package com.crmax.controller;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Interaction;
import com.crmax.persistence.model.Product;
import com.crmax.persistence.service.ContactService;
import com.crmax.persistence.service.InteractionService;
import com.crmax.persistence.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class InteractionsController {

    @Autowired
    private InteractionService interactionService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ProductService productService;

    @Autowired
    private Environment env;

    @GetMapping(value = "/client-detail-page")
    public String showProductsPage(@ModelAttribute("status") String status,
                                   @ModelAttribute("errorMessage") String errorMessage,
                                   @ModelAttribute("successMessage") String successMessage,
                                   Model model,
                                   @RequestParam(value = "contact", required = true) String contactEmail){

        Contact selectedContact = contactService.findByEmail(contactEmail);

        List<Interaction> interactions =
                interactionService.findByContact(selectedContact);

        List<String> stages =
                interactionService.getStages();

        List<Product> products =
                productService.findAllActiveProducts();


        model.addAttribute("contact", selectedContact);
        model.addAttribute("interactions", interactions);
        model.addAttribute("stages", stages);
        model.addAttribute("products", products);

        if(!model.containsAttribute("interaction")){
            model.addAttribute("interaction", new Interaction());
        }

        model.addAttribute("client", selectedContact);
        model.addAttribute("status", status);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("successMessage", successMessage);

        return "client-detail";
    }

    @PostMapping(value = "/save-interaction")
    public RedirectView saveProduct(@Valid @ModelAttribute("interaction")Interaction interaction,
                                   @ModelAttribute("contactEmail")String contactEmail,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        System.out.println("Interaction : " + interaction);
        System.out.println("Interaction : " + interaction.getProductsSelected());
        System.out.println("Interaction : " + contactEmail);

        if(interaction.getProductsSelected() != null){
            List<Product> selectedProducts = productService.findAllByIds(interaction.getProductsSelected());

            interaction.setProducts(selectedProducts);
        }

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("interaction", interaction);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.interaction", bindingResult);
            return new RedirectView("client-detail-page");
        }

        Contact selectedContact = contactService.findByEmail(contactEmail);

        String status = interactionService.save(interaction, selectedContact);

        redirectAttributes.addFlashAttribute("status", status);
        redirectAttributes.addAttribute("contact", contactEmail);

        if(status == "ERROR")
            redirectAttributes.addFlashAttribute("errorMessage", env.getProperty("client_detail.error"));
        else
            redirectAttributes.addFlashAttribute("successMessage", env.getProperty("client_detail.success"));

        return new RedirectView("client-detail-page");
    }

}

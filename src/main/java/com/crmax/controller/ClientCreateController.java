package com.crmax.controller;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import com.crmax.persistence.service.ContactService;
import com.crmax.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class ClientCreateController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private Environment env;

    @GetMapping(value = "/create-client")
    public String showCreateClientForm(@ModelAttribute("status") String status,
                                            Model model){
        if(!model.containsAttribute("client")){
            model.addAttribute("client", new Contact());
        }

        model.addAttribute("status", status);

        return "create-client-form";
    }

    @PostMapping(value = "/save-client")
    public RedirectView saveClient(@Valid @ModelAttribute("client")Contact contact,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("client", contact);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.client", bindingResult);
            return new RedirectView("create-client");
        }

        if(!contactService.isDuplicate(contact)){
            redirectAttributes.addFlashAttribute("status", contactService.save(contact));
        } else {
            redirectAttributes.addFlashAttribute("status",
                    ContactService.InsertionStatus.WARNING.name());
        }

        return new RedirectView("create-client");
    }

    @GetMapping(value = "/delete-client")
    public String deleteClient(@RequestParam(name = "clientEmail")String clientEmail){

        System.out.println("client email : " + clientEmail);

        Contact contact = contactService.findByEmail(clientEmail);
        System.out.println("client : " + contact);
        contactService.deleteContact(contact);

        return "redirect:/crmax-dashboard";

    }

    @PostMapping(value = "/update-client")
    public RedirectView updateClient(@Valid @ModelAttribute("client")Contact contact,
                                     @ModelAttribute("contactEmail")String contactEmail,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("client", contact);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.client", bindingResult);
            return new RedirectView("create-client");
        }

        String status = "";

        System.out.println("cached email : " + contactEmail);
        Contact selectedContact = contactService.findByEmail(contactEmail);

        System.out.println("old contact : " + selectedContact);
        System.out.println("new contact : " + contact);
        status = contactService.updateContact(selectedContact, contact);
        redirectAttributes.addFlashAttribute("status", status);

        redirectAttributes.addAttribute("contact", contact.getEmail());

        if(status == "SUCCESS")
            redirectAttributes.addFlashAttribute("successMessage", env.getProperty("create_form.success_update"));
        else
            redirectAttributes.addFlashAttribute("errorMessage", env.getProperty("create_form.error_update"));

        return new RedirectView("client-detail-page");
    }


}

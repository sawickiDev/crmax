package com.crmax.controller;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Product;
import com.crmax.persistence.service.ContactService;
import com.crmax.persistence.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/products-page")
    public String showProductsPage(@ModelAttribute("status") String status,
                                   Model model){

        List<Product> products =
                productService.findAllActiveProducts();

        model.addAttribute("products", products);

        if(!model.containsAttribute("product")){
            model.addAttribute("product", new Product());
        }

        model.addAttribute("status", status);

        return "products-page";
    }

    @GetMapping(value = "/remove-product")
    public String deleteProduct(@RequestParam(value = "productId", required = true) String productId){

        Product product = productService.findById(productId);

        productService.removeProduct(product);

        return "redirect:/products-page";

    }

    @PostMapping(value = "/save-product")
    public RedirectView saveProduct(@Valid @ModelAttribute("product")Product product,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("product", product);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            return new RedirectView("products-page");
        }

        if(!productService.isDuplicate(product)){
            redirectAttributes.addFlashAttribute("status", productService.save(product));
        } else {
            redirectAttributes.addFlashAttribute("status",
                    ProductService.InsertionStatus.WARNING.name());
        }

        return new RedirectView("products-page");
    }


}

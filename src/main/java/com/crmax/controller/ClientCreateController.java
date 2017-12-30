package com.crmax.controller;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import com.crmax.persistence.service.ContactService;
import com.crmax.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping(value = "/create-client")
    public String showCreateClientForm(@ModelAttribute("status") String status,
                                            Model model){

        System.out.println(model.asMap().get("client"));
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


}

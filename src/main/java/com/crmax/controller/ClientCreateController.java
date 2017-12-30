package com.crmax.controller;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import com.crmax.persistence.service.ContactService;
import com.crmax.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientCreateController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/create-client")
    public ModelAndView showCreateClientForm(){
        return new ModelAndView("create-client-form", "client", new Contact());
    }

    @PostMapping(value = "/save-client")
    public String saveClient(@ModelAttribute("client")Contact contact, BindingResult result){
        if (result.hasErrors()){
            return "error";
        }

        String currentlyLoggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentlyLoggedUser = (User)userService.loadUserByUsername(currentlyLoggedUsername);

        contact.setOwnerId(currentlyLoggedUser);

        System.out.println(contact);

        System.out.println(contactService.save(contact));

        return "redirect:crmax-dashboard";
    }


}

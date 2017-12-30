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

@Controller
public class ClientCreateController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/create-client")
    public ModelAndView showCreateClientForm(@ModelAttribute("status") String status,
                                            ModelAndView modelAndView){
        modelAndView.addObject("status", status);
        modelAndView.addObject("client", new Contact());
        modelAndView.setViewName("create-client-form");

        return modelAndView;
    }

    @PostMapping(value = "/save-client")
    public RedirectView saveClient(@ModelAttribute("client")Contact contact,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes){

        if(!contactService.isDuplicate(contact)){
            redirectAttributes.addFlashAttribute("status", contactService.save(contact));
        } else {
            redirectAttributes.addFlashAttribute("status",
                    ContactService.InsertionStatus.WARNING.name());
        }

        return new RedirectView("create-client");
    }


}

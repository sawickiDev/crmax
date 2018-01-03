package com.crmax.controller;

import com.crmax.persistence.dao.UserDao;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import com.crmax.persistence.service.ContactService;
import com.crmax.persistence.service.InteractionService;
import com.crmax.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private InteractionService interactionService;

    @GetMapping(value = "/crmax-dashboard")
    public String showDashboard(Model model){
        List<Contact> contacts =
                contactService.findByUsers(userService.getCurrentlyLoggedUserWithSubordinates());
        
        model.addAttribute("compoundContacts", contactService.createCompoundList(contacts));

        return "crmax-dashboard";
    }

}

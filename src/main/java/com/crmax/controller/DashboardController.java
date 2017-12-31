package com.crmax.controller;

import com.crmax.persistence.dao.UserDao;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import com.crmax.persistence.service.ContactService;
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

    @GetMapping(value = "/crmax-dashboard")
    public String showDashboard(Model model){
        List<Contact> contacts =
                contactService.findByUser(userService.getCurrentlyLoggedUser());

        System.out.println(contacts);

        model.addAttribute("contacts", contacts);

        return "crmax-dashboard";
    }

}

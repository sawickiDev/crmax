package com.crmax.controller;

import com.crmax.persistence.model.User;
import com.crmax.persistence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/crmax-register")
    public String showRegistrationForm(@ModelAttribute("status") String status,
                                       Model model){

        User user = new User();
        model.addAttribute("user", user);

        List<User> admins = userService.findAllAdmins();
        model.addAttribute("admins", admins);

        model.addAttribute("status", status);

        System.out.println("ADMINS : " +admins);
        System.out.println("STATUS : " +status);

        return "crmax-registration";
    }

    @PostMapping(value = "/register-user")
    public RedirectView createUser(@ModelAttribute("user") @Valid User user,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            System.out.println("ERRORS");
            System.out.println(bindingResult);
            System.out.println(bindingResult.getAllErrors());

            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            return new RedirectView("crmax-register");
        }

        String status = userService.saveUser(user);

        System.out.println("STATUS : " + status);

        redirectAttributes.addFlashAttribute("status", status);

        return new RedirectView("crmax-register");
    }

}

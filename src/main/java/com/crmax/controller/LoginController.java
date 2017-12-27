package com.crmax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/crmax-login")
    public String showLoginPage(){

        return "crmax-login";
    }

}

package com.crmax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientCreateController {

    @GetMapping(value = "/create-client")
    public String showCreateClientForm(){
        return "create-client-form";
    }

}

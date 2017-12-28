package com.crmax.controller;

import com.crmax.persistence.dao.UserDao;
import com.crmax.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @GetMapping(value = "/crmax-dashboard")
    public String showDashboard(Model model){
        return "crmax-dashboard";
    }

}

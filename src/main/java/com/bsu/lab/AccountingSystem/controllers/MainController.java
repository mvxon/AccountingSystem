package com.bsu.lab.AccountingSystem.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MainController {
    @RequestMapping({"", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }



}


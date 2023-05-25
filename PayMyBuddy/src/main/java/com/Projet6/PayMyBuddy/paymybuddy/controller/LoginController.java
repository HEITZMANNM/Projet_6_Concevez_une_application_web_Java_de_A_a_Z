package com.Projet6.PayMyBuddy.paymybuddy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @RequestMapping("/test")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
    @GetMapping("/hello")
    public String publicPage()
    {
        return "Hello!!";
    }

    @GetMapping("/private")
    public String privatePage(Authentication authentication)
    {
        return "Bienvenida ~["+ authentication.getName() +"]~";
    }
}

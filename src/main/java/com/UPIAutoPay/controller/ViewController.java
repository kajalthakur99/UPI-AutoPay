package com.UPIAutoPay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewController {

    @GetMapping(value = "/login")
   public String indexPage()
    {
        System.out.println("index page");
        return "login";
    }
}

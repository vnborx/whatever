package com.vnborx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
public class TestController {
    @RequestMapping("/test")
    public String test(Model model) {
        model.addAttribute("msg", "<h1>Hello, Spring Boot.</h1>");
        model.addAttribute("users", Arrays.asList("Acacia", "Milo"));

        return "test";
    }
}

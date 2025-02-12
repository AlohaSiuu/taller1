package com.example.restaurante.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index"; // Carga index.html desde templates
    }

    @GetMapping("/form")
    public String form() {
        return "form"; // Carga form.html desde templates
    }
    
    @GetMapping("/list")
    public String list() {
        return "list"; // Carga list.html desde templates
    }
    

}


package com.wendyecommerce.biuwendyecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // Redirige la raíz "/" directamente a tu pantalla de login estática
    @GetMapping("/")
    public String index() {
        return "forward:/login.html"; 
    }
}
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 安全controller
 */
@Controller
public class SecurityController {

    @GetMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("/userLogin")
    public String userLogin(){
        return "login";
    }
}

package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world Controller
 *
 * @author tianfeng
 * @date 2017/12/22
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String home() {
        return "Hello World!";
    }
}

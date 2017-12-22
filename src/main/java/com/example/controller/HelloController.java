package com.example.controller;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "hello", httpMethod = "POST")
    public String hello() {
        return "Hello World!";
    }
}

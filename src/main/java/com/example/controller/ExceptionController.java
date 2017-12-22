package com.example.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tianfeng on 2017/12/22.
 */
@Controller
@RequestMapping("/exceptionController")
public class ExceptionController {

    @RequestMapping("/trigger")
    @ApiOperation(value = "触发抛异常", httpMethod = "GET")
    public void trigger() {
        throw new RuntimeException("this is runtime exception ...");
    }
}

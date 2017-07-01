package com.example.controller;

import com.example.entity.Contact;
import com.example.service.ContactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by tianfeng on 2017/4/16.
 */
@Controller
@RequestMapping(value = "/contacts")
@Api("联系人管理的api")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "或得主页", httpMethod = "GET")
    public String home(Map<String, Object> model) {
        System.out.println("the root is accessed...");
        model.put("contacts", contactService.findAll());
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "保存联系人", httpMethod = "GET")
    public String submit(Contact contact) {
        System.out.println("the param is " + contact.toString());
        System.out.println("the root is accessed...");
        return "redirect:/";
    }
}

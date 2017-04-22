package com.example.controller;

import com.example.model.domain.Contact;
import com.example.service.ContactService;
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
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String, Object> model) {
        System.out.println("the root is accessed...");
        model.put("contacts", contactService.findAll());
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String submit(Contact contact) {
        System.out.println("the param is "+contact.toString());
        System.out.println("the root is accessed...");
        return "redirect:/";
    }
}

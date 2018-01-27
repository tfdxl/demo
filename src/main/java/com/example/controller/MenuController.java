package com.example.controller;

import com.example.entity.Menu;
import com.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/menu/getMenu/{id}")
    @ResponseBody
    public Menu getMenu(@PathVariable("id") Long id) {
        System.err.println("the id is " + id);
        return this.menuService.getMenu(id);
    }
}

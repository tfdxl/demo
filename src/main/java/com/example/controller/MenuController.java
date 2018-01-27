package com.example.controller;

import com.example.entity.Menu;
import com.example.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ResponseBody
    @RequestMapping(value = "/menu/getMenu/{id}", method = RequestMethod.GET)
    public Menu getMenu(@PathVariable("id") Long id) {
        System.err.println("the id is " + id);
        return this.menuService.getMenu(id);
    }

    @GetMapping("/now.json")
    public @ResponseBody Map now() {
        Map map = new HashMap<>();
        map.put("time", new Date());
        return map;
    }
}

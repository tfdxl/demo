package com.example.service.impl;

import com.example.entity.Menu;
import com.example.service.MenuService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService{

    @Cacheable(value = "menu")
    @Override
    public Menu getMenu(Long id) {
        System.err.println("not from cache ... ");
        return new Menu();
    }
}

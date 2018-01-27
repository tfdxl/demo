package com.example;

import com.example.entity.Menu;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {

    public static void main(String[] args) {
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<Menu> entity = restTemplate.getForEntity("http://localhost:8000/menu/getMenu/1", Menu.class);
        final Menu menu = entity.getBody();
        System.err.println(menu);
    }
}

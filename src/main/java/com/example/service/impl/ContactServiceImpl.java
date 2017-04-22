package com.example.service.impl;

import com.example.model.domain.Contact;
import com.example.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianfeng on 2017/4/16.
 */
@Service
public class ContactServiceImpl implements ContactService{

    @Override
    public List<Contact> findAll() {

        List<Contact> contacts = new ArrayList<>();
        for(int i=0;i<10;i++){
            contacts.add(new Contact((long)1000+i,"adcddd"+i,"fddfds"+i,"1234567"+i,"fdafdsfds"));
        }
        return contacts;
    }
}

package com.example.service;

import com.example.model.domain.Contact;

import java.util.List;

/**
 * Created by tianfeng on 2017/4/16.
 */
public interface ContactService {

    List<Contact> findAll();
}

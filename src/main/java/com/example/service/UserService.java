package com.example.service;

import com.example.entity.User;
import org.springframework.data.repository.query.Param;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tianfeng on 2017/5/30.
 */
public interface UserService {

    User findOne(Long id);

    User save(User user);

    //采用的是hql的查询方式？
    User findUserByName(@Param("name") String name);

    long count();

    Boolean login(String username, String password, HttpServletRequest request);
}

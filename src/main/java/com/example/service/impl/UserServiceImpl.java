package com.example.service.impl;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

/**
 * Created by tianfeng on 2017/5/30.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByName(@Param("name") String name) {
        return userRepository.findUserByName(name);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public Boolean login(String username, String password, HttpServletRequest request) {

        boolean result = false;
        User user = userRepository.findByUsernameAndPwd(username, password);
        //成功的话要把用户放到session
        if (user != null) {
            request.getSession().setAttribute("user", user);
            System.out.println("The user is login ... "+user);
            result = true;
        }
        return result;
    }
}

package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by tianfeng on 2017/5/30.
 */
@Controller
@RequestMapping("/hibernate")
@Api("hibernate测试用的api")
public class HibernateController {

    @Autowired
    private UserService userService;

    //将返回的bean作为返回体的一部分
    @RequestMapping("getUserById")
    @ResponseBody
    @ApiOperation(value = "通过主键获取用户",httpMethod ="POST", response = User.class,notes = "通过主键获取用户")
    public User getUserById(Long id) {
        User u = userService.findOne(id);
        System.out.println("userRepository: " + userService);
        System.out.println("id: " + id);
        return u;
    }

    @RequestMapping("saveUser")
    @ResponseBody
    public void saveUser() {
        User u = new User();
        u.setUserName("wan");
        u.setAddress("江西省上饶市鄱阳县");
        u.setBirthDay(new Date());
        u.setSex("男");
        userService.save(u);
    }

    @RequestMapping("count")
    @ResponseBody
    public long count(){
        return userService.count();
    }

}

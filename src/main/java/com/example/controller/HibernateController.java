package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @ApiOperation(value = "通过主键获取用户", httpMethod = "POST", response = User.class, notes = "通过主键获取用户")
    public User getUserById(Long id, HttpServletRequest request) {

        User u = userService.findOne(id);
        System.out.println("userRepository: " + userService);
        System.out.println("id: " + id);
        return u;
    }

    @RequestMapping("saveUser")
    @ResponseBody
    @ApiOperation(value = "保存用户", httpMethod = "POST")
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
    @ApiOperation(value = "获得总数", httpMethod = "GET")
    public long count() {
        return userService.count();
    }

    @RequestMapping("getUserList")
    @ResponseBody
    @ApiOperation(value = "获得列表", httpMethod = "GET")
    public Result getUserList() {

        List<User> list = new ArrayList<User>();
        list.add(new User("tianfeng", new Date(), "男", "浙江杭州", "123").setId(123L));
        list.add(new User("monlie", new Date(), "男", "浙江杭州", "123").setId(456L));
        list.add(new User("mengmeng", new Date(), "男", "浙江杭州", "123").setId(789L));
        list.add(new User("xiaomeng", new Date(), "男", "浙江杭州", "123").setId(234L));
        list.add(new User("bee", new Date(), "男", "浙江杭州", "123").setId(567L));
        return Result.success(list);
    }

}

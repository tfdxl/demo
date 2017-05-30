package com.example.controller;

import com.example.constants.ResponseCode;
import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tianfeng on 2017/5/30.
 */
@Controller
@RequestMapping("/login")
@Api("登录用api")
public class LoginController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/login.json")
    @ApiOperation(value = "用户登录接口",httpMethod ="POST", response = Result.class,notes = "用户登录接口")
    public Result login(@ApiParam(name = "username", value = "用户名") @RequestParam(name="username") String username,  @RequestParam(name="password")@ApiParam(name = "password", value = "密码") String password, HttpServletRequest request) {
        if (userService.login(username, password, request)) {
            return Result.success("登录成功!");
        } else {
            return Result.fail(ResponseCode.LOGIN_FAILED);
        }
    }

}

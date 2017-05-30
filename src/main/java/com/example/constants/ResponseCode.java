package com.example.constants;

/**
 * Created by tianfeng on 2017/5/30.
 */
public enum ResponseCode {

    OK(200, "ok"),
    LOGIN_FAILED(500, "登录失败，用户名或者密码错误");


    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

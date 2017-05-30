package com.example.utils;

import com.example.constants.ResponseCode;

/**
 * Created by tianfeng on 2017/5/30.
 */
public class Result<T> {

    private int statusCode;
    private String message;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {

        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {

        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public static Result<?> success(Object data) {
        return new Result(ResponseCode.OK.getCode(), ResponseCode.OK.getMessage(), data);
    }

    public static Result<?> fail(ResponseCode responseCode) {
        return new Result(responseCode.getCode(), responseCode.getMessage(), false);
    }
}


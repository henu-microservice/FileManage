package com.henu.seafile.util;

/**
 * @Author Pangpd
 * @Description
 * @Date 2019/5/14 19:35
 */
public class ResponseModel {
    private int code; //自定义状态码
    private Object data;
    private String message; //结果描述

    public ResponseModel() {
    }

    public ResponseModel(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

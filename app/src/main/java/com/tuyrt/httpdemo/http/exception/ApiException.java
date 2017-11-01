package com.tuyrt.httpdemo.http.exception;

/**
 * 自定义异常可以自行修改
 * Created by：hcs on 2016/10/17 16:08
 * e_mail：aaron1539@163.com
 */
public class ApiException extends RuntimeException {
    public int code;
    public String message;

    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}

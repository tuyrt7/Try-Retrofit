package com.tuyrt.httpdemo.http.entity;

/**
 * 数据请求结果统一预处理实体类（要求服务器返回数据统一格式）
 * 如数据格式为：
 * <p>
 * {
 * "code": 200,
 * "message": "成功",
 * "data": {}
 * }
 * <p>
 * data字段可以是数组字符串等，根据需求而定。
 * 可以具体需求进行更改该类字段格式
 */
public class BaseEntity<T> {

    private static int SUCCESS_CODE = 200;//成功的code
    private int code;
    private String message;
    private T data;


    public boolean isSuccess() {
        return getCode() == SUCCESS_CODE;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

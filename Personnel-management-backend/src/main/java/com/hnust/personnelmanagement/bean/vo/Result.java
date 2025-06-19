package com.hnust.personnelmanagement.bean.vo;

/**
 *  统一返回结果格式
 */
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result() {}

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功返回
    public static Result success(String message, Object data) {
        return new Result(200, message, data);
    }

    // 错误返回
    public static Result error(Integer code, String message) {
        return new Result(code, message, null);
    }

    // getter和setter方法
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

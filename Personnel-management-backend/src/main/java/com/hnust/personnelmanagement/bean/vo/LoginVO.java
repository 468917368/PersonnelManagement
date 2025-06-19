package com.hnust.personnelmanagement.bean.vo;

/**
 *  接收登录请求参数
 */
public class LoginVO {
    private String username;
    private String password;

    // getter和setter方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

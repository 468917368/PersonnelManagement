package com.hnust.personnelmanagement.service;


import com.hnust.personnelmanagement.bean.dto.HrEmployee;
import com.hnust.personnelmanagement.bean.dto.User;

public interface UserService {
    /**
     * 登录账号密码校验
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);


}

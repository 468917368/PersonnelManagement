package com.hnust.personnelmanagement.service.impl;

import com.hnust.personnelmanagement.bean.dto.User;
import com.hnust.personnelmanagement.mapper.UserMapper.UserMapper;
import com.hnust.personnelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username, password);
    }
}

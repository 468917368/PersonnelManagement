package com.hnust.personnelmanagement.mapper.UserMapper;

import com.hnust.personnelmanagement.bean.dto.User;
import org.apache.ibatis.annotations.Mapper;

/**
 *  登陆时,返回给前端的User类的mapper
 */
@Mapper
public interface UserMapper {

    User selectByUsernameAndPassword(String username, String password);
}

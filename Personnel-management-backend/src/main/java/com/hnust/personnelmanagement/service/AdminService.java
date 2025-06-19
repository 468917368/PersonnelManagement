package com.hnust.personnelmanagement.service;

import com.hnust.personnelmanagement.bean.dto.HrEmployee;
import com.hnust.personnelmanagement.bean.repository.SysUser;

import java.util.List;

public interface AdminService {

    /**
     * 获取用户列表
     * @return
     */
    List<SysUser> getUserList();

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int deleteUserById(Long id);

    /**
     *  根据id查询用户
     * @param id
     * @return
     */
    boolean getUserById(Long id);

    /**
     * 添加用户
     * @param userInfo
     * @param employeeInfo
     * @param roleId
     * @return
     */
    int addUser(SysUser userInfo, HrEmployee employeeInfo, Long roleId);
}

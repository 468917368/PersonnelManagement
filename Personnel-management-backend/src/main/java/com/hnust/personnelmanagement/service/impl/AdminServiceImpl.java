package com.hnust.personnelmanagement.service.impl;

import com.hnust.personnelmanagement.bean.dto.HrEmployee;
import com.hnust.personnelmanagement.bean.dto.SysUserRole;
import com.hnust.personnelmanagement.bean.repository.SysUser;
import com.hnust.personnelmanagement.mapper.UserMapper.HrEmployeeMapper;
import com.hnust.personnelmanagement.mapper.UserMapper.SysUserMapper;
import com.hnust.personnelmanagement.mapper.UserMapper.SysUserRoleMapper;
import com.hnust.personnelmanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private HrEmployeeMapper hrEmployeeMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;



    @Override
    public int addUser(SysUser userInfo, HrEmployee employeeInfo, Long roleId) {
        //插入sys_user表
        int res = sysUserMapper.insertSysUser(userInfo);
        //获取自增id
        SysUser sysUser = sysUserMapper.getSysUserByUsername(userInfo.getUsername());
        Long id = sysUser.getId();
        //插入hr_employee表
        res += hrEmployeeMapper.insertHrEmployee(employeeInfo,userInfo.getRealName(),userInfo.getPhone(),userInfo.getEmail());
        //插入sys_user_role表
        SysUserRole sysUserRole = new SysUserRole(null, id, roleId);
        res += sysUserRoleMapper.insertSysUserRole(sysUserRole);
        return res;
    }

    @Override
    public boolean getUserById(Long id) {
        return sysUserMapper.selectById( id);
    }

    @Override
    public int deleteUserById(Long id) {
        return sysUserMapper.deleteUserById(id);
    }

    @Override
    public List<SysUser> getUserList() {
        return sysUserMapper.selectAllUser();
    }
}

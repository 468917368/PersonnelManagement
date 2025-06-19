package com.hnust.personnelmanagement.bean.dto;

import com.hnust.personnelmanagement.bean.repository.SysUser;
import lombok.Data;

@Data
public class   AddUserDto {
    private SysUser userInfo;        // 对应sys_user表
    private HrEmployee employeeInfo; // 对应hr_employee表
    private Long roleId;             // 对应sys_user_role的角色ID
}

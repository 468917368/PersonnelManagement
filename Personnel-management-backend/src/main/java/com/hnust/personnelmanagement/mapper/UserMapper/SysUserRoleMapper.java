package com.hnust.personnelmanagement.mapper.UserMapper;

import com.hnust.personnelmanagement.bean.dto.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysUserRoleMapper {

    /**
     * 添加用户角色
     * @param sysUserRole
     * @return
     */
    int insertSysUserRole(SysUserRole sysUserRole);
}
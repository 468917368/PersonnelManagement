package com.hnust.personnelmanagement.bean.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户角色关联表
 * sys_user_role
 */
@AllArgsConstructor
@Data
public class SysUserRole implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    private static final long serialVersionUID = 1L;
}
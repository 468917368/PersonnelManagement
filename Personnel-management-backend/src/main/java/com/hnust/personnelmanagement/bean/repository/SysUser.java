package com.hnust.personnelmanagement.bean.repository;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户表
 * sys_user
 *
 *  用于返回用户个人信息
 */
@Data
public class SysUser implements Serializable {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 状态(0:禁用,1:正常)
     */
    private int status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
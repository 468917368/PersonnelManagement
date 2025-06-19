package com.hnust.personnelmanagement.bean.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 员工信息表
 * hr_employee
 */
@Data
public class HrEmployee implements Serializable {
    /**
     * 员工ID
     */
    private Long id;

    /**
     * 员工编号
     */
    private String employeeNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别(0:女,1:男)
     */
    private Boolean gender;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 住址
     */
    private String address;

    /**
     * 学历
     */
    private String education;

    /**
     * 职位ID
     */
    private Long positionId;

    private String positionName;  // 职位名称

    /**
     * 部门ID
     */
    private Long departmentId;

    private String departmentName;  // 部门名称


    /**
     * 入职日期
     */
    private Date entryDate;

    /**
     * 状态(0:离职,1:在职)
     */
    private Boolean status;

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
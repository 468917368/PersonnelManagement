package com.hnust.personnelmanagement.bean.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 部门表
 * hr_department
 */
@Data
public class HrDepartment implements Serializable {
    /**
     * 部门ID
     */
    private Long id;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 父部门ID
     */
    private Long parentId;

    private String parentName;

    /**
     * 部门负责人ID
     */
    private Long leaderId;

    private String leaderName;

    /**
     * 部门描述
     */
    private String description;

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
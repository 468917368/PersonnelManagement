package com.hnust.personnelmanagement.bean.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 人事调整记录表
 * hr_transfer
 */
@Data
public class HrTransfer implements Serializable {
    /**
     * 调动ID
     */
    private Long id;

    /**
     * 员工ID
     */
    private Long employeeId;

    private String employeeName;

    private String employeeNo;

    /**
     * 调动前部门ID
     */
    private Long beforeDepartmentId;

    private String beforeDepartmentName;

    /**
     * 调动后部门ID
     */
    private Long afterDepartmentId;

    private String afterDepartmentName;

    /**
     * 调动前职位ID
     */
    private Long beforePositionId;

    private String beforePositionName;

    /**
     * 调动后职位ID
     */
    private Long afterPositionId;

    private String afterPositionName;

    /**
     * 调动类型(1:部门调动,2:职位调动,3:同时调动)
     */
    private Boolean transferType;

    /**
     * 调动原因
     */
    private String transferReason;

    /**
     * 调动日期
     */
    private Date transferDate;

    /**
     * 审批人ID
     */
    private Long approverId;

    private String approverName;

    /**
     * 状态(0:待审批,1:已审批,2:已拒绝)
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
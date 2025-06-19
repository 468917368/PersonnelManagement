package com.hnust.personnelmanagement.bean.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 薪酬表
 * hr_salary
 */
@Data
public class HrSalary implements Serializable {
    /**
     * 薪酬ID
     */
    private Long id;

    /**
     * 员工ID
     */
    private Long employeeId;

    /**
     * 基本工资
     */
    private BigDecimal basicSalary;

    /**
     * 职位工资
     */
    private BigDecimal positionSalary;

    /**
     * 绩效工资
     */
    private BigDecimal performanceSalary;

    /**
     * 奖金
     */
    private BigDecimal bonus;

    /**
     * 保险
     */
    private BigDecimal insurance;

    /**
     * 个税
     */
    private BigDecimal tax;

    /**
     * 实发工资
     */
    private BigDecimal actualSalary;

    /**
     * 工资月份(格式:yyyy-MM)
     */
    private String salaryMonth;

    /**
     * 状态(0:未发放,1:已发放)
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
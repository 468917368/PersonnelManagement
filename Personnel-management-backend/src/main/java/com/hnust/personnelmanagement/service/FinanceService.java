package com.hnust.personnelmanagement.service;

import com.hnust.personnelmanagement.bean.dto.HrSalary;

import java.util.List;

public interface FinanceService {

    /**
     * 根据员工id、工资年份,月份查询员工工资
     * @param employeeId
     * @param salaryYear
     * @param salaryMonth
     * @return
     */
    List<HrSalary> getSalarys(Long employeeId, String salaryYear, String salaryMonth);

    /**
     * 根据薪资id查询员工工资
     * @param id
     * @return
     */
    HrSalary getSalaryById(Long id);

    /**
     * 修改员工工资
     * @param salaryInfo
     * @return
     */
    int modifySalary(HrSalary salaryInfo);
}

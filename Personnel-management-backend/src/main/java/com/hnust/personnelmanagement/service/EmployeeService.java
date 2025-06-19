package com.hnust.personnelmanagement.service;

import com.hnust.personnelmanagement.bean.dto.HrEmployee;
import com.hnust.personnelmanagement.bean.dto.HrSalary;

public interface EmployeeService {

    /**
     * 根据用户名查询用户个人信息
     * @param realName
     * @return
     */
    HrEmployee getUserInfo(String realName);

    /**
     * 修改用户信息
     * @param employeeInfo
     * @return
     */
    int modifyEmployeeInfo(HrEmployee employeeInfo);

    /**
     * 根据id、salaryYear、salaryMonth查询工资信息
     * @param id
     * @param salaryYear
     * @param salaryMonth
     * @return
     */
    HrSalary getSalaryInfo(String id, String salaryYear, String salaryMonth);
}

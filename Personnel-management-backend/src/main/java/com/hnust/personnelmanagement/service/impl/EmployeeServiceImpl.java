package com.hnust.personnelmanagement.service.impl;

import com.hnust.personnelmanagement.bean.dto.HrEmployee;
import com.hnust.personnelmanagement.bean.dto.HrSalary;
import com.hnust.personnelmanagement.mapper.UserMapper.HrEmployeeMapper;
import com.hnust.personnelmanagement.mapper.UserMapper.HrSalaryMapper;
import com.hnust.personnelmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private HrEmployeeMapper hrEmployeeMapper;

    @Autowired
    private HrSalaryMapper hrSalaryMapper;

    @Override
    public HrSalary getSalaryInfo(String id, String salaryYear, String salaryMonth) {
        return hrSalaryMapper.selectByIdAndSalaryYearAndSalaryMonth(id, salaryYear, salaryMonth);
    }

    @Override
    public int modifyEmployeeInfo(HrEmployee employeeInfo) {
        String name = employeeInfo.getName();
        String phone = employeeInfo.getPhone();
        String email = employeeInfo.getEmail();
        String address = employeeInfo.getAddress();
        return hrEmployeeMapper.updateByRealName(name, phone, email, address);
    }

    @Override
    public HrEmployee getUserInfo(String realName) {
        return hrEmployeeMapper.selectByUsername(realName);
    }
}

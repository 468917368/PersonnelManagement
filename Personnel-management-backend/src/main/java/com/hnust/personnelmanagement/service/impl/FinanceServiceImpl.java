package com.hnust.personnelmanagement.service.impl;

import com.hnust.personnelmanagement.bean.dto.HrSalary;
import com.hnust.personnelmanagement.mapper.UserMapper.HrSalaryMapper;
import com.hnust.personnelmanagement.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private HrSalaryMapper hrSalaryMapper;

    @Override
    public int modifySalary(HrSalary salaryInfo) {
        return hrSalaryMapper.updateSalary(salaryInfo);
    }

    @Override
    public HrSalary getSalaryById(Long id) {
        return hrSalaryMapper.selectById(id);
    }

    @Override
    public List<HrSalary> getSalarys(Long employeeId, String salaryYear, String salaryMonth) {
        return hrSalaryMapper.selectByIdOrSalaryYearOrSalaryMonth(employeeId, salaryYear, salaryMonth);
    }
}

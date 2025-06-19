package com.hnust.personnelmanagement.service.impl;

import com.hnust.personnelmanagement.bean.dto.HrDepartment;
import com.hnust.personnelmanagement.mapper.UserMapper.HrDepartmentMapper;
import com.hnust.personnelmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl  implements DepartmentService {



    @Autowired
    private HrDepartmentMapper departmentMapper;

    @Override
    public int updateDepartment(Long id, HrDepartment department) {
        department.setId(id);
        return departmentMapper.updateDepartment(department);
    }

    @Override
    public HrDepartment getDepartmentById(Long id) {
        return departmentMapper.selectDepartmentById(id);
    }

    @Override
    public int deleteDepartment(Long id) {
        return departmentMapper.deleteDepartment(id);
    }

    @Override
    public int addDepartment(HrDepartment department) {
        return departmentMapper.insertDepartment(department);
    }

    @Override
    public List<HrDepartment> getAllDepartments() {
        return departmentMapper.selectAllDepartments();
    }
}

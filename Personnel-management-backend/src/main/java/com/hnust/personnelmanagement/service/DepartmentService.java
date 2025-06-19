package com.hnust.personnelmanagement.service;

import com.hnust.personnelmanagement.bean.dto.HrDepartment;

import java.util.List;

public interface DepartmentService {
    /**
     * 获取所有部门信息
     * @return
     */
    List<HrDepartment> getAllDepartments();

    /**
     * 添加部门
     */
    int addDepartment(HrDepartment department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    int deleteDepartment(Long id);

    /**
     * 根据id获取部门信息
     * @param id
     * @return
     */
    HrDepartment getDepartmentById(Long id);

    /**
     * 修改部门信息
     * @param id
     * @param department
     * @return
     */
    int updateDepartment(Long id, HrDepartment department);
}

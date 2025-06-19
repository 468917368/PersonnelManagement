package com.hnust.personnelmanagement.mapper.UserMapper;


import com.hnust.personnelmanagement.bean.dto.HrDepartment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HrDepartmentMapper {

    /**
     * 查询所有部门信息
     * @return
     */
    List<HrDepartment> selectAllDepartments();

    /**
     * 添加部门信息
     * @param department
     * @return
     */
    int insertDepartment(HrDepartment department);

    /**
     * 删除部门信息
     * @param id
     * @return
     */
    int deleteDepartment(Long id);

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    HrDepartment selectDepartmentById(Long id);

    /**
     * 更新部门信息
     * @param department
     * @return
     */
    int updateDepartment(HrDepartment department);
}
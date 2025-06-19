package com.hnust.personnelmanagement.mapper.UserMapper;

import com.hnust.personnelmanagement.bean.dto.HrSalary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HrSalaryMapper {

    /**
     * 根据员工id、工资年份、工资月份查询员工工资信息
     * @param id
     * @param salaryYear
     * @param salaryMonth
     * @return
     */
    HrSalary selectByIdAndSalaryYearAndSalaryMonth(String id, String salaryYear, String salaryMonth);

    /**
     * 根据员工id、或者工资年份、或者工资月份查询员工工资信息
     * @param employeeId
     * @param salaryYear
     * @param salaryMonth
     * @return
     */
    List<HrSalary> selectByIdOrSalaryYearOrSalaryMonth(Long employeeId, String salaryYear, String salaryMonth);

    /**
     * 根据薪资id查询员工工资信息
     * @param id
     * @return
     */
    HrSalary selectById(Long id);

    /**
     * 修改员工工资信息
     * @param salaryInfo
     * @return
     */
    int updateSalary(HrSalary salaryInfo);
}
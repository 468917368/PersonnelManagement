package com.hnust.personnelmanagement.mapper.UserMapper;


import com.hnust.personnelmanagement.bean.dto.HrEmployee;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface HrEmployeeMapper {

    /**
     * 根据用户名查询用户信息
     * @param realName
     * @return
     */
    HrEmployee selectByUsername(String realName);


    /**
     * 根据真实姓名修改员工信息
     * @param name
     * @param phone
     * @param email
     * @param address
     * @return
     */
    int updateByRealName(String name, String phone, String email, String address);


    /**
     *  添加员工信息
     * @param employeeInfo
     * @param realName
     * @param phone
     * @param email
     * @return
     */
    int insertHrEmployee(HrEmployee employeeInfo, String realName, String phone, String email);
}
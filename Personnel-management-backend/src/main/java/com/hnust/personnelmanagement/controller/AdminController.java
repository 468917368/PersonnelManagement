package com.hnust.personnelmanagement.controller;


import com.hnust.personnelmanagement.bean.dto.AddUserDto;
import com.hnust.personnelmanagement.bean.dto.HrEmployee;
import com.hnust.personnelmanagement.bean.repository.SysUser;
import com.hnust.personnelmanagement.bean.vo.Result;
import com.hnust.personnelmanagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pmsys/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /**
     *  添加用户
     * @param addUserDto 封装三个表中的用户信息
     * @return
     */
    @PostMapping("/add")
    public Result addUser(@RequestBody AddUserDto  addUserDto){
         // 获取用户信息
         SysUser userInfo = addUserDto.getUserInfo();
         HrEmployee employeeInfo = addUserDto.getEmployeeInfo();
         Long roleId = addUserDto.getRoleId();
         int result = adminService.addUser(userInfo, employeeInfo, roleId);
         if ( result != 0)
              return Result.success("添加用户成功", result);
         return Result.error(500, "添加用户失败");
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUserById(@PathVariable Long id) {
            adminService.deleteUserById(id);
            if( adminService.getUserById(id))
                 return Result.error(500, "删除用户失败");
            return Result.success("删除用户成功", null);
    }


    /**
     * 获取员工列表
     * @return
     */
    @GetMapping("/userList")
    public Result getUserList(){
        List<SysUser> userList=adminService.getUserList();
        if (userList!=null)
            return Result.success("获取用户列表成功",userList);
        return Result.error(500,"获取用户列表失败");
    }
}

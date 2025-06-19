package com.hnust.personnelmanagement.controller;


import com.hnust.personnelmanagement.bean.dto.HrEmployee;
import com.hnust.personnelmanagement.bean.dto.HrSalary;
import com.hnust.personnelmanagement.bean.vo.Result;
import com.hnust.personnelmanagement.service.EmployeeService;
import com.hnust.personnelmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/pmsys")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 根据id、salaryYear、salaryMonth查询工资信息
     * @param id
     * @param salaryYear
     * @param salaryMonth
     * @return
     */
    @GetMapping("/employee/salaryInfo")
    public Result getSalaryInfo(@RequestParam String id, @RequestParam String salaryYear, @RequestParam String salaryMonth){
        HrSalary salaryInfo = employeeService.getSalaryInfo(id, salaryYear, salaryMonth);
        if (salaryInfo != null)
            return Result.success("获取用户工资信息成功", salaryInfo);
        else return Result.error(500, "获取用户工资信息失败");
    }

    /**
     * 根据真实姓名修改用户信息
     * @param employeeInfo
     * @return
     */
    @PutMapping("/employee/update")
    public Result modifyEmployeeInfo(@RequestBody HrEmployee employeeInfo) {
        int res = employeeService.modifyEmployeeInfo(employeeInfo);
        if(res != 0 )
            return Result.success("修改用户信息成功", res);
        else
            return Result.error(500, "修改用户信息失败");
    }


    /**
     *根据用户真实姓名返回用户信息
     * employee
     * @param realName
     * @return
     */
    @GetMapping("/employee/info/{realName}")
    public Result PersonalInfo(@PathVariable String realName) {
        HrEmployee userInfo = employeeService.getUserInfo(realName);
        return Result.success("获取用户信息成功", userInfo);
    }
}

package com.hnust.personnelmanagement.controller;


import com.hnust.personnelmanagement.bean.dto.HrSalary;
import com.hnust.personnelmanagement.bean.vo.Result;
import com.hnust.personnelmanagement.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pmsys")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    /**
     * 更新员工工资信息
     * @param salaryInfo
     * @return
     */
    @PutMapping("/finance/salary")
    public Result updateSalary(@RequestBody HrSalary salaryInfo){
        int res = financeService.modifySalary(salaryInfo);
        if (res != 0)
            return Result.success("更新薪资信息成功", null);
        return Result.error(500, "更新薪资信息失败");
    }

    /**
     * 根据薪资id查询一条员工工资信息
     * @param id
     * @return
     */
    @GetMapping("/finance/salary/{id}")
    public Result getSalaryById(@PathVariable Long id){
        HrSalary salaryInfo = financeService.getSalaryById(id);
        if (salaryInfo != null)
            return Result.success("获取员工工资信息成功", salaryInfo);
        else return Result.error(500, "获取员工工资信息失败");
    }

    /**
     * 根据员工id、工资年份、工资月份查询工资信息
     * @param employeeId
     * @param salaryYear
     * @param salaryMonth
     * @return
     */
    @GetMapping("/finance/salarys")
    public Result getSalarys(@RequestParam(required = false)  Long employeeId,
                             @RequestParam(required = false)  String salaryYear,
                             @RequestParam(required = false)  String salaryMonth){
        // 空字符串转 null
        salaryYear = StringUtils.hasText(salaryYear)  ? salaryYear : null;
        salaryMonth = StringUtils.hasText(salaryMonth)  ? salaryMonth : null;

        List<HrSalary> salaryList = financeService.getSalarys(employeeId, salaryYear, salaryMonth);
        if(salaryList != null && !salaryList.isEmpty())
            return Result.success("获取员工工资信息成功", salaryList);
        return Result.error(500, "获取员工工资信息失败,请检查查询参数");
    }
}

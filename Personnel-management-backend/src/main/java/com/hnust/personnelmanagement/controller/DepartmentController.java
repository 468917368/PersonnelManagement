package com.hnust.personnelmanagement.controller;

import com.hnust.personnelmanagement.bean.dto.HrDepartment;
import com.hnust.personnelmanagement.bean.vo.Result;
import com.hnust.personnelmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("pmsys")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 修改部门信息
     * @param id
     * @param department
     * @return
     */
    @PutMapping("/department/{id}")
    public Result updateDepartment(@PathVariable Long id, @RequestBody HrDepartment department){
        int res = departmentService.updateDepartment(id,department);
        if(res!=0)
            return Result.success("更新部门信息成功", null);
        return Result.error(500, "更新部门信息失败");
    }

    /**
     * 编辑部门信息,首先根据部门id查询部门信息,在此基础上进行修改
     * @param id
     * @return
     */
    @GetMapping("/department/{id}")
    public Result getDepartmentById(@PathVariable Long id){
        HrDepartment hrDepartment = departmentService.getDepartmentById(id);
        if(hrDepartment!=null)
            return Result.success("查询部门成功", hrDepartment);
        return Result.error(500, "查询部门失败");
    }

    /**
     * 根据部门id删除部门
     * @param id
     * @return
     */
    @DeleteMapping("/department/delete/{id}")
    public Result deleteDepartment( @PathVariable Long id){
        int res = departmentService.deleteDepartment(id);
        if (res!=0)
            return Result.success("删除部门成功", null);
        return Result.error(500, "删除部门失败");
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @PostMapping("/department/add")
    public Result addDepartment(@RequestBody HrDepartment department) {  //  @RequestBody 不要忘记
        System.out.println(department.toString());
        int res = departmentService.addDepartment(department);
        if(res!=0){
            return Result.success("添加部门成功", null);
        }
        return Result.error(500, "添加部门失败");
    }

    /**
     * 获取所有部门信息
     * @return
     */
    @GetMapping("/department/list")
    public Result getAllDepartments() {
        List<HrDepartment> departmentList = departmentService.getAllDepartments();
        if (departmentList == null)
            return Result.error(500, "获取所有部门信息失败");
        return Result.success("获取所有部门信息成功", departmentList);
    }
}

package com.hnust.personnelmanagement.controller;


import com.hnust.personnelmanagement.bean.dto.ApprovalTransfer;
import com.hnust.personnelmanagement.bean.dto.HrTransfer;
import com.hnust.personnelmanagement.bean.vo.Result;
import com.hnust.personnelmanagement.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pmsys/transfer")
public class HrController {

    @Autowired
    private HrService hrService;

    /**
     * 调动审批
     * @param approvalTransfer 一个类接收审批的信息
     * @return
     */
    @PostMapping("/approvalOrNot")
    public Result approvalOrNot(@RequestBody ApprovalTransfer approvalTransfer) {
        int res = hrService.approvalOrNot(approvalTransfer.getId(), approvalTransfer.getStatus());
        if (res != 0){
            return Result.success("操作成功",res);
        }else
            return Result.error(500, "操作失败");
    }

    /**
     * 获取未审批的调动列表
     * @return
     */
    @GetMapping("/notApply")
    public Result getNotTransferList() {
        List<HrTransfer> transferList = hrService.getNotTransferList();
        if(transferList != null)
            return Result.success("获取未完成调动列表成功",transferList);
        return Result.error(500, "获取未完成调动列表失败");
    }

    /**
     * 获取已完成调动列表
     * @return
     */
    @GetMapping("/completedTransferList")
    public Result getCompletedTransferList() {
        List<HrTransfer> transferList =hrService.getCompletedTransferList();
        if(transferList != null)
            return Result.success("获取已审批的调动列表成功",transferList);
        return Result.error(500, "获取已审批的调动列表失败");
    }
}

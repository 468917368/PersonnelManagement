package com.hnust.personnelmanagement.service;

import com.hnust.personnelmanagement.bean.dto.HrTransfer;

import java.util.List;

public interface HrService {
    /**
     * 获取已完成的调动列表
     * @return
     */
    List<HrTransfer> getCompletedTransferList();

    /**
     * 获取未审批的调动列表
     * @return
     */
    List<HrTransfer> getNotTransferList();

    /**
     * 调动审批
     * @param id
     * @param status
     * @return
     */
    int approvalOrNot(Long id, int status);
}

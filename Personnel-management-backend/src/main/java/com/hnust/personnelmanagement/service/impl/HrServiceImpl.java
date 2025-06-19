package com.hnust.personnelmanagement.service.impl;

import com.hnust.personnelmanagement.bean.dto.HrTransfer;
import com.hnust.personnelmanagement.mapper.UserMapper.HrTransferMapper;
import com.hnust.personnelmanagement.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrServiceImpl implements HrService {

    @Autowired
    private HrTransferMapper hrTransferMapper;

    @Override
    public int approvalOrNot(Long id, int status) {
        return hrTransferMapper.updateStatus(id, status);
    }

    @Override
    public List<HrTransfer> getNotTransferList() {
        return hrTransferMapper.selectNotTransferList();
    }

    @Override
    public List<HrTransfer> getCompletedTransferList() {
        return hrTransferMapper.selectCompletedTransferList();
    }
}

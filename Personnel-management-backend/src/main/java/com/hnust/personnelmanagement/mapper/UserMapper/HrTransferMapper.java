package com.hnust.personnelmanagement.mapper.UserMapper;

import com.hnust.personnelmanagement.bean.dto.HrTransfer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HrTransferMapper {

    /**
     * 查询已完成的调动
     * @return
     */
    List<HrTransfer> selectCompletedTransferList();

    /**
     * 查询未审批的调动
     * @return
     */
    List<HrTransfer> selectNotTransferList();

    /**
     * 修改调动状态
     * @param id
     * @param status 0：未审批，1：已审批，2：已驳回
     * @return
     */
    int updateStatus(Long id, int status);
}
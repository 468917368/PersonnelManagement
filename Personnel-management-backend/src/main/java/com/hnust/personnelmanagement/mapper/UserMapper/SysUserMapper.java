package com.hnust.personnelmanagement.mapper.UserMapper;



import com.hnust.personnelmanagement.bean.repository.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 表sys_user的mapper接口
 */
@Mapper
@Repository
public interface SysUserMapper {

    /**
     * 查询所有用户
     * @return
     */
    List<SysUser> selectAllUser();

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    int deleteUserById(Long id);

    /**
     *  根据id查询用户
     * @param id
     * @return
     */
    boolean selectById(Long id);

    /**
     *  添加sys_user
     * @param userInfo
     * @return
     */
    int insertSysUser(SysUser userInfo);

    /**
     *  根据用户名查询用户
     * @param username
     * @return
     */
    SysUser getSysUserByUsername(String username);
}
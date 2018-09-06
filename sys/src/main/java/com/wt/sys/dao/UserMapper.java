package com.wt.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wt.sys.domain.User;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
  * 用户表 Mapper 接口
 * </p>
 *
 * @author wt
 * @since 2018-08-10
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查找用户
     * @param username
     * @return
     */
    User selectByName(@Param("username") String username);
}
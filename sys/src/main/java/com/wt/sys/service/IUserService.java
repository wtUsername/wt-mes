package com.wt.sys.service;

import com.google.common.base.Optional;

import com.baomidou.mybatisplus.service.IService;
import com.wt.sys.domain.User;


/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wt
 * @since 2018-08-10
 */
public interface IUserService extends IService<User> {
    /**
     * 查找用户
     * @param username
     * @return
     */
    Optional<User> selectByName(String username);
}

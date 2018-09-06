package com.wt.sys.service.serviceImpl;

import com.google.common.base.Optional;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wt.sys.dao.UserMapper;
import com.wt.sys.domain.User;
import com.wt.sys.service.IUserService;

import org.springframework.stereotype.Service;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wt
 * @since 2018-08-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 查找用户
     *
     * @param username
     * @return
     */
    @Override
    public Optional<User> selectByName(String username) {
        if (StringUtils.isEmpty(username)) {
            return Optional.absent();
        }
        User user = baseMapper.selectByName(username);
        if (user == null) {
            return Optional.absent();
        }
        return Optional.of(user);
    }
}

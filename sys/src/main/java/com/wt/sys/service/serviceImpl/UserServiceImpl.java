package com.wt.sys.service.serviceImpl;

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
     * @param password
     * @return
     */
    @Override
    public boolean selectByName(String username, String password) {
        User user = baseMapper.selectByName(username,password);
        if(user == null){
            return false;
        }else {
            return true;
        }
    }
}

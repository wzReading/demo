package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/16 20:00
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User register(User user) {
        userMapper.insert(user);
        return user;
    }

    @Override
    public int updatePasswordByName(String email, String password) {
        User user = getUserByEmail(email);
        user.setPassword(password);
        return userMapper.updateById(user);
    }

    @Override
    public User getUserByEmail(String email) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email",email);
        return userMapper.selectOne(queryWrapper);
    }


}

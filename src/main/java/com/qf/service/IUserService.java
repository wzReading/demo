package com.qf.service;

import com.qf.entity.User;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/16 19:59
 */
public interface IUserService {

    public User login(String username, String password);

    public User register(User user);

    public int updatePasswordByName(String email,String password);

    public User getUserByEmail(String email);
}

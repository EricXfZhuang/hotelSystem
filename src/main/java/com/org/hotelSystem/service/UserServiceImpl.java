package com.org.hotelSystem.service;

import com.org.hotelSystem.mapper.UserMapper;
import com.org.hotelSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean register(User user) {
        return userMapper.insert(user) != 0;
    }

    @Override
    public boolean login(String userName, String password) {
        // TODO
        return false;
    }

    @Override
    public User getUser(String userName) {
        User user =  userMapper.selectByUserName(userName);
        user.setPassword("*****");
        return user;
    }
}

package com.org.hotelSystem.service;

import com.org.hotelSystem.model.User;

public interface UserService {
    boolean register(User user);
    boolean login(String userName, String password);
    User getUser(String userName);
}

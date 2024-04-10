package com.evan.example.provider;

import com.evan.example.common.model.User;
import com.evan.example.common.service.UserService;

public class UserServiceImpl implements UserService {
    public User getUser(User user) {
        System.out.println("用户名: " + user.getName());
        return user;
    }
}

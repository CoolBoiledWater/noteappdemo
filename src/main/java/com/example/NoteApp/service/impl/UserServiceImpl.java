package com.example.NoteApp.service.impl;

import com.example.NoteApp.entity.User;
import com.example.NoteApp.mapper.UserMapper;
import com.example.NoteApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserName(User user) {
        return userMapper.getUserByUserName( user.getUserName(), user.getUserPassword()) ;
    }
}

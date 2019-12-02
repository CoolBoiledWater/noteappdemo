package com.example.NoteApp.service;

import com.example.NoteApp.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public interface UserService {

    public User getUserByUserName(User user);
}

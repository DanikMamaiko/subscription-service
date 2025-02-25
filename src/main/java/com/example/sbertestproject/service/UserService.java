package com.example.sbertestproject.service;

import com.example.sbertestproject.dto.UserDto;
import com.example.sbertestproject.entity.User;

public interface UserService {
    User saveUser(User user);
    User updateUser(User user);
    UserDto getUserById(Long userId);
    void deleteUser(Long userId);
}

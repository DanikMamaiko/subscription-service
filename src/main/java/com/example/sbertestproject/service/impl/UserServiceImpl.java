package com.example.sbertestproject.service.impl;

import com.example.sbertestproject.dto.UserDto;
import com.example.sbertestproject.entity.User;
import com.example.sbertestproject.mapper.UserMapper;
import com.example.sbertestproject.repository.UserRepository;
import com.example.sbertestproject.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User saveUser(User user) {
        log.info("Saving user: {}", user);
        User savedUser = userRepository.save(user);
        log.info("User saved: {}", savedUser);
        return savedUser;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        log.info("Updating user: {}", user);
        User updatedUser = userRepository.save(user);
        log.info("User updated: {}", updatedUser);
        return updatedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long userId) {
        log.info("Fetching user with ID: {}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
        UserDto userDto = userMapper.toUserDto(user);
        log.info("User found: {}", userDto);
        return userDto;
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        log.info("Deleting user with ID: {}", userId);
        userRepository.deleteById(userId);
        log.info("User with ID {} deleted", userId);
    }
}

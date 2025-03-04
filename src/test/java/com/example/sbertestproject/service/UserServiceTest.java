package com.example.sbertestproject.service;

import com.example.sbertestproject.dto.UserDto;
import com.example.sbertestproject.entity.User;
import com.example.sbertestproject.mapper.UserMapper;
import com.example.sbertestproject.repository.UserRepository;
import com.example.sbertestproject.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserServiceTest  {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Test
    void getUserByIdTest() {
        User user = new User(1L, "Danik", "danmam@gmail.com", Set.of());
        UserDto userDto = new UserDto(1L, "Danik", "danmam@gmail.com");

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userMapper.toUserDto(user)).thenReturn(userDto);

        UserDto serviceUserDto = userService.getUserById(1L);

        Assertions.assertEquals(userDto, serviceUserDto);
        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(userMapper, Mockito.times(1)).toUserDto(user);
    }
}

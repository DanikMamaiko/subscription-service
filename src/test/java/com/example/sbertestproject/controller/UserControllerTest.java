package com.example.sbertestproject.controller;

import com.example.sbertestproject.dto.UserDto;
import com.example.sbertestproject.entity.User;
import com.example.sbertestproject.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserServiceImpl userService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();

        userDto = UserDto.builder()
                .id(1L)
                .username("danik")
                .email("danmam@gmail.com")
                .build();

        user = User.builder()
                .id(1L)
                .username("danik")
                .email("danmam@gmail.com")
                .subscriptions(Set.of())
                .build();
    }

    @Test
    void getUserByIdTest() throws Exception {
        Mockito.when(userService.getUserById(1L)).thenReturn(userDto);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        Mockito.verify(userService, Mockito.times(1)).getUserById(1L);
    }

    @Test
    void addUserTest() throws Exception {
        String userJson = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk());
        Mockito.verify(userService, Mockito.times(1)).saveUser(user);
    }
}

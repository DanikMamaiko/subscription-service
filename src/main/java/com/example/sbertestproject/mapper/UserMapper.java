package com.example.sbertestproject.mapper;

import com.example.sbertestproject.dto.UserDto;
import com.example.sbertestproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
}

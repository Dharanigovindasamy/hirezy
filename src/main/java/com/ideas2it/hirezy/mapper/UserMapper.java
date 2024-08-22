package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.UserDto;
import com.ideas2it.hirezy.model.User;

public class UserMapper {

    public static User mapUserDto(UserDto userDto) {
        return User.builder()
                .userName(userDto.getUserName())
                .emailId(userDto.getEmailId())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .address(userDto.getAddress())
                .build();
    }
}

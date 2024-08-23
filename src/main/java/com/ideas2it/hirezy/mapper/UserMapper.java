package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.UserDto;
import com.ideas2it.hirezy.model.User;

/**
 * <p>
 *  This is the Mapper class for the User.
 *  It is used to map the User Details from the UserDto to user.
 *  And also from the User to UserDto.
 * </p>
 * @author paari
 */
public class UserMapper {

    public static User mapUserDto(UserDto userDto) {
        return User.builder()
                .userName(userDto.getUserName())
                .emailId(userDto.getEmailId())
                .password(userDto.getPassword())
                .phoneNumber(userDto.getPhoneNumber())
                .build();

    }
}

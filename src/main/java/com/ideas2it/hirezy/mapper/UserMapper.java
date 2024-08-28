package com.ideas2it.hirezy.mapper;

import com.ideas2it.hirezy.dto.UserDto;
import com.ideas2it.hirezy.model.User;

import java.util.Optional;

/**
 * <p>
 *  This is the Mapper class for the User.
 *  It is used to map the User Details from the UserDto to user.
 *  And also from the User to UserDto.
 * </p>
 * @author paari
 */
public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .userName(userDto.getUserName())
                .emailId(userDto.getEmailId())
                .phoneNumber(userDto.getPhoneNumber())
                .build();

    }

    public static  UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .emailId(user.getEmailId())
                .phoneNumber(user.getPhoneNumber())
                .userName(user.getUsername())
                .build();
    }
}

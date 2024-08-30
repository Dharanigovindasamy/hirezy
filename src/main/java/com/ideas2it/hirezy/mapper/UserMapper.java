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

    /**
     * <p>
     *     The method used for conversion of userDto to user entity
     * </p>
     * @param userDto -{@link UserDto} from the user
     * @return User - user entity details to the user
     */
    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .emailId(userDto.getEmailId())
                .phoneNumber(userDto.getPhoneNumber())
                .build();

    }

    /**
     * <p>
     *      The method used for conversion of user entity to userDto entity
     * </p>
     * @param user - user details from the user
     * @return userDto -{@link UserDto} given to the user
     */
    public static  UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .emailId(user.getEmailId())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}

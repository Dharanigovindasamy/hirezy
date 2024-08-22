package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.UserDto;
import com.ideas2it.hirezy.mapper.UserMapper;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(UserDto userDto) {
        User user = UserMapper.mapUserDto(userDto);
        userRepository.save(user);
    }

}

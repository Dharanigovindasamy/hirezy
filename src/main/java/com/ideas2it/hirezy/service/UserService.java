package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {


    String saveUser(UserDto userDto);

    String generateUserToken(UserDto userDto);

//    UserDto retrieveUser();
//
//    void updateUSer();
//
//    void deleteUSer();
}

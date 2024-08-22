package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.UserDto;

public interface UserService {


    void saveUser(UserDto userDto);

    UserDto retrieveUser();

    void updateUSer();

    void deleteUSer();
}

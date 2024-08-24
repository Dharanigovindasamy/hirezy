package com.ideas2it.hirezy.controller;

import com.ideas2it.hirezy.dto.UserDto;
import com.ideas2it.hirezy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String createUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserDto userDto) {
        return userService.generateUserToken(userDto);
    }
}

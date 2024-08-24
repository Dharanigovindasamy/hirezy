package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.dto.UserDto;
import com.ideas2it.hirezy.mapper.UserMapper;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String saveUser(UserDto userDto) {
        String email = userDto.getEmailId();
        for (User user : userRepository.findAll()){
            if(email.equals(user.getEmailId())){
                return "User already exist Please Log in";
            }
        }
        User user = UserMapper.mapUserDto(userDto);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "SignUp Success";
    }
    public String generateUserToken(UserDto UserDto) {
        User users = UserMapper.mapUserDto(UserDto);
        String email = users.getEmailId();
        String password = users.getPassword();
        for(User user : userRepository.findAll()) {
            if(email.equals(user.getEmailId()) && BCrypt.checkpw(password,user.getPassword())){
                return "Token Generated";
            }
        }
        return "Check your Mail and Password";
    }
}

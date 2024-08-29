package com.ideas2it.hirezy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.exception.ResourceNotFoundException;
import com.ideas2it.hirezy.model.User;
import com.ideas2it.hirezy.repository.UserRepository;

/**
 * <p>
 *     This class used for user details informations
 * </p>
 * @author dharani.govindhasamy
 * @version 1
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User retrieveUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with Id: "+ userId));
    }
}

package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.model.User;
import org.springframework.stereotype.Service;

/**
 * This class is the service class for the user.
 * @author paari
 */
@Service
public interface UserService {

    /**
     * This method is to retrieve the user by Id.
     * @param userId
     *     It is the Id of the user to be retrieved.
     * @return User
     *     It contains the user details.
     */
    User retrieveUserById(Long userId);
}

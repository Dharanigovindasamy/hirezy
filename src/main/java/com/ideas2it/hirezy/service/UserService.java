package com.ideas2it.hirezy.service;

import org.springframework.stereotype.Service;

import com.ideas2it.hirezy.model.User;

/**
 * <p>
 *     This class used for User details performs which can do business logic based on user
 * </p>
 * @author dharani.govindhasamy
 * @version 1
 */
@Service
public interface UserService {

    /**
     * <p>
     *     Retrieve user details by providing user Id
     * </p>
     * @param userId - id of the user
     * @return User - user object of the given user id
     */
    User retrieveUserById(Long userId);
}

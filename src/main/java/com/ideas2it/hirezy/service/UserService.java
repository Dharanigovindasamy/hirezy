package com.ideas2it.hirezy.service;

import com.ideas2it.hirezy.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    User retrieveUserById(Long userId);
}

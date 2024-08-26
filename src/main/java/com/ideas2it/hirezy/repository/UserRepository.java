package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);

    Optional<User> findByEmailId(String email);
}

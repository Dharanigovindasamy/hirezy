package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * This class is the repository for the User.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    /**
     *  Find the user by mail Id
     * @param email - Smail id of the user
     * @return User - user object of the mail holder
     */
    Optional<User> findByEmailId(String email);
}

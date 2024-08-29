package com.ideas2it.hirezy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ideas2it.hirezy.model.User;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *     User interface extends from JPA repository and perform CRUD operations
 * </p>
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

package com.ideas2it.hirezy.repository;

import com.ideas2it.hirezy.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}

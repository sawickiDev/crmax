package com.crmax.persistence.dao;

import com.crmax.persistence.model.Role;
import com.crmax.persistence.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    User findByUsername(String username);
    List<User> findAllByRoleId(Role role);
    List<User> findByUsernameOrEmail(String username, String email);

}

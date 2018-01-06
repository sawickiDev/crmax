package com.crmax.persistence.service;

import com.crmax.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getCurrentlyLoggedUser();
    List<User> getCurrentlyLoggedUserWithSubordinates();
    List<User> findAllAdmins();
    String saveUser(User user);
    List<User> findByUsernameAndEmail(User user);

    public enum InsertionStatus {
        WARNING,
        ERROR,
        SUCCESS
    }
}

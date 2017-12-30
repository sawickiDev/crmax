package com.crmax.persistence.service;

import com.crmax.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getCurrentlyLoggedUser();
}

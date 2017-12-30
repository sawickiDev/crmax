package com.crmax.persistence.service;

import com.crmax.persistence.dao.UserDao;
import com.crmax.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public User findByUsername(String username) {

        System.out.println("user by username : " + userDao.findByUsername(username));

        return userDao.findByUsername(username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User not found");

        return user;
    }

    public User getCurrentlyLoggedUser() {

        String currentlyLoggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentlyLoggedUser = (User)loadUserByUsername(currentlyLoggedUsername);

        return currentlyLoggedUser;
    }
}

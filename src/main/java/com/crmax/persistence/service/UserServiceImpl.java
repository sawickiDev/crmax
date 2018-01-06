package com.crmax.persistence.service;

import com.crmax.persistence.dao.UserDao;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Role;
import com.crmax.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

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

        System.out.println("authorities : " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        User currentlyLoggedUser = (User)loadUserByUsername(currentlyLoggedUsername);

        return currentlyLoggedUser;
    }

    public List<User> getCurrentlyLoggedUserWithSubordinates() {

        List<User> users = new ArrayList<User>();

        User currenltyLogged = getCurrentlyLoggedUser();
        users.add(currenltyLogged);
        users.addAll(currenltyLogged.getSubordinates());

        return users;
    }

    @Override
    public List<User> findAllAdmins() {

        Role adminRole = roleService.findRoleByName("ADMIN_USER_ROLE");
        List<User> admins = userDao.findAllByRoleId(adminRole);

        return admins;
    }

    @Override
    public String saveUser(User user) {
        User persistedUser;

        System.out.println("USER TO SAVE : " + user);
        Role standardRole = roleService.findRoleByName("STANDARD_USER_ROLE");
        user.setRoleId(standardRole);

        User supervisor = findByUsername(user.getSupervisorCache());
        user.setSupervisor(supervisor);
        System.out.println("USER TO SAVE : " + user);

        if(!isDuplicate(user)){

            try{
                persistedUser = userDao.save(user);
            } catch (Exception ex){
                ex.printStackTrace();
                return ContactService.InsertionStatus.ERROR.name();
            }

        } else {
            return InsertionStatus.WARNING.name();
        }

        return resolveInsertionStatus(persistedUser);
    }

    @Override
    public List<User> findByUsernameAndEmail(User user) {
        return userDao.findByUsernameOrEmail(user.getUsername(), user.getEmail());
    }

    private String resolveInsertionStatus(User persistedContact){

        if(persistedContact != null)
            return ContactService.InsertionStatus.SUCCESS.name();
        else
            return ContactService.InsertionStatus.ERROR.name();

    }

    private Boolean isDuplicate(User user){

        return findByUsernameAndEmail(user).size() > 0;
    }
}

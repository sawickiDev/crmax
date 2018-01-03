package com.crmax.persistence.dao;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDao extends CrudRepository<Contact, Integer> {
    List<Contact> findByOwnerId(User user);
    List<Contact> findByEmailOrPhone(String email, String phone);
    List<Contact> findByOwnerIdIsIn(List<User> users);
    Contact findByEmail(String email);
}

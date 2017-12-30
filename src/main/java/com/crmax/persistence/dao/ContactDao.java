package com.crmax.persistence.dao;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactDao extends JpaRepository<Contact, Integer>{
    List<Contact> findByUser(User user);
}

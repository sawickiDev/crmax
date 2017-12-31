package com.crmax.persistence.service;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;

import java.util.List;

public interface ContactService {
    String save(Contact contact);
    List<Contact> findByUser(User user);
    List<Contact> findByUsers(List<User> users);
    List<Contact> findByEmailAndPhone(Contact contact);
    Boolean isDuplicate(Contact contact);

    public enum InsertionStatus {
        WARNING,
        ERROR,
        SUCCESS
    }
}

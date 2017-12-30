package com.crmax.persistence.service;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;

import java.util.List;

public interface ContactService {
    Contact save(Contact contact);
    List<Contact> findByUser(User user);
}

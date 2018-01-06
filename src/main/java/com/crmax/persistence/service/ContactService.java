package com.crmax.persistence.service;

import com.crmax.persistence.model.CompoundContact;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;

import java.util.List;

public interface ContactService {
    String save(Contact contact);
    List<Contact> findByUser(User user);
    List<Contact> findByUsers(List<User> users);
    List<Contact> findByEmailAndPhone(Contact contact);
    Contact findByEmail(String email);
    Boolean isDuplicate(Contact contact);
    List<CompoundContact> createCompoundList(List<Contact> rawContacts);
    String updateContact(Contact oldContact, Contact newContact);
    void deleteContact(Contact contact);

    public enum InsertionStatus {
        WARNING,
        ERROR,
        SUCCESS
    }
}

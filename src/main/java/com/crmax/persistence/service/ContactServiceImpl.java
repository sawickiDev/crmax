package com.crmax.persistence.service;

import com.crmax.persistence.dao.ContactDao;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private UserService userService;

    @Transactional
    public String save(Contact contact) {

        Contact persistedContact;

        try{
            contact.setOwnerId(userService.getCurrentlyLoggedUser());
            persistedContact = contactDao.save(contact);
        } catch(DataIntegrityViolationException dvex) {
            return InsertionStatus.ERROR.name();
        }

        return resolveInsertionStatus(persistedContact);
    }

    public List<Contact> findByUser(User user) {

        return contactDao.findByOwnerId(user);
    }

    public List<Contact> findByUsers(List<User> users) {

        System.out.println("subs contacts");
        System.out.println(contactDao.findByOwnerIdIsIn(users));

        return contactDao.findByOwnerIdIsIn(users);
    }

    public List<Contact> findByEmailAndPhone(Contact contact) {
        System.out.println(contactDao.findByEmailOrPhone(contact.getEmail(), contact.getPhone()));
        return contactDao.findByEmailOrPhone(contact.getEmail(), contact.getPhone());
    }

    @Override
    public Contact findByEmail(String email) {
        return contactDao.findByEmail(email);
    }

    private String resolveInsertionStatus(Contact persistedContact){

        if(persistedContact != null)
            return InsertionStatus.SUCCESS.name();
        else
            return InsertionStatus.ERROR.name();

    }

    public Boolean isDuplicate(Contact contact){

        return findByEmailAndPhone(contact).size() > 0;
    }
}

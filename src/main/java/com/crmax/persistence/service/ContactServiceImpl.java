package com.crmax.persistence.service;

import com.crmax.persistence.dao.ContactDao;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactDao contactDao;

    @Transactional
    public Contact save(Contact contact) {
        return null;
    }

    public List<Contact> findByUser(User user) {
        return contactDao.findByUser(user);
    }
}

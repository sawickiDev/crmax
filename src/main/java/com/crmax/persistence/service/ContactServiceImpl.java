package com.crmax.persistence.service;

import com.crmax.persistence.dao.ContactDao;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactDao contactDao;

    @Transactional
    public Contact save(Contact contact) {
        return contactDao.save(contact);
    }

    public List<Contact> findByUser(User user) {
        return contactDao.findByOwnerId(user);
    }
}

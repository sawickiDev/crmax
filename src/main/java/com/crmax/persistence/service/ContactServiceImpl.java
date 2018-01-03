package com.crmax.persistence.service;

import com.crmax.persistence.dao.ContactDao;
import com.crmax.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private InteractionService interactionService;

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

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        Interaction interaction = new Interaction();
        interaction.setStage(InteractionService.Stage.NEWLY_CREATED.valueString);
        interaction.setStartDate(java.sql.Date.valueOf(LocalDate.now()));
        interaction.setEndDate(java.sql.Date.valueOf(LocalDate.now()));

        interactionService.save(interaction, persistedContact);

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

    @Override
    public List<CompoundContact> createCompoundList(List<Contact> rawContacts) {
        List<CompoundContact> compoundContacts =
                new ArrayList<>();

        for(Contact contact : rawContacts) {
            List<Interaction> interactions = interactionService.findByContact(contact);
            compoundContacts
                    .add(new CompoundContact(contact, getValueOfInteractions(interactions), interactions.get(interactions.size()-1).getStage()));
        }

        return compoundContacts;
    }

    private String getValueOfInteractions(List<Interaction> interactions) {
        Double overallPrice = 0.00;

        for(Interaction interaction : interactions){
            for(Product product : interaction.getProducts()) {
                overallPrice += product.getPrice();
            }
        }

        return String.valueOf(overallPrice);
    }
}

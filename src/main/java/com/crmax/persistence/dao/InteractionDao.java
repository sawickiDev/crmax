package com.crmax.persistence.dao;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Interaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionDao extends CrudRepository<Interaction, Integer>{
    List<Interaction> findByContactId(Contact contact);
}

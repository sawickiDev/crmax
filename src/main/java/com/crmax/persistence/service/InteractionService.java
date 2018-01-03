package com.crmax.persistence.service;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Interaction;
import com.crmax.persistence.model.User;

import java.util.List;

public interface InteractionService {
    String save(Interaction interaction, Contact contact);
    List<Interaction> findByContact(Contact contact);
    List<String> getStages();

    public enum InsertionStatus {
        WARNING,
        ERROR,
        SUCCESS
    }

    public enum Stage {
        NEWLY_CREATED("newly created"),
        FOLLOW_UP("follow up"),
        NEGOTIATING("negotiating"),
        PURCHASE("purchase"),
        AFTER_SALES("after-sales");

        String valueString;

        private Stage(String valueString){
            this.valueString = valueString;
        }
    }
}

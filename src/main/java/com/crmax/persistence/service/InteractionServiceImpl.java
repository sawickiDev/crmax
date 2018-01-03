package com.crmax.persistence.service;

import com.crmax.persistence.dao.InteractionDao;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InteractionServiceImpl implements InteractionService {

    @Autowired
    private InteractionDao interactionDao;

    @Override
    @Transactional
    public String save(Interaction interaction, Contact contact) {
        Interaction persistedInteraction;

        try {
            interaction.setContactId(contact);
            persistedInteraction = interactionDao.save(interaction);
        } catch (DataIntegrityViolationException dvex) {
            return ContactService.InsertionStatus.ERROR.name();
        }

        return resolveInsertionStatus(persistedInteraction);
    }

    @Override
    public List<Interaction> findByContact (Contact contact){
        return interactionDao.findByContactId(contact);
    }

    @Override
    public List<String> getStages() {
        List<Stage> stages =  new ArrayList<>(Arrays.asList(InteractionService.Stage.values()));
        List<String> stageStrings = new ArrayList<>();

        for(Stage stage : stages){
            stageStrings.add(stage.valueString);
        }

        return stageStrings;
    }

    private String resolveInsertionStatus (Interaction persistedInteraction){

        if (persistedInteraction != null)
            return InteractionService.InsertionStatus.SUCCESS.name();
        else
            return InteractionService.InsertionStatus.ERROR.name();

    }

}

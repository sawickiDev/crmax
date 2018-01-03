package com.crmax.persistence.model;

public class CompoundContact {

    private Contact contact;
    private String value;
    private String stage;

    public CompoundContact() {
    }

    public CompoundContact(Contact contact, String value, String stage) {
        this.contact = contact;
        this.value = value;
        this.stage = stage;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}

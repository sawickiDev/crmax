package com.crmax.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "interactions_table")
public class Interaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interaction_id")
    private Integer id;

    @Column(name = "stage")
    private String stage;

    @Column(name = "last_name")
    private String startDate;

    @Column(name = "email")
    private String lastDate;

    @Size(max = 30, message = "Company Name is too long")
    @Column(name = "company_name")
    private String companyName;

    @JoinColumn(name = "contact_id")
    @ManyToOne
    private Contact contactId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "interactions_products_table",
                joinColumns = {@JoinColumn(name = "interaction_id")},
                inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<Product> products = new ArrayList<>();

    public Interaction() {
    }

    public Interaction(String stage, String startDate, String lastDate, @Size(max = 30, message = "Company Name is too long") String companyName) {
        this.stage = stage;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.companyName = companyName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Contact getContactId() {
        return contactId;
    }

    public void setContactId(Contact contactId) {
        this.contactId = contactId;
    }

    @Override
    public String toString() {
        return "Interaction{" +
                "id=" + id +
                ", stage='" + stage + '\'' +
                ", startDate='" + startDate + '\'' +
                ", lastDate='" + lastDate + '\'' +
                ", companyName='" + companyName + '\'' +
                ", contactId=" + contactId +
                '}';
    }
}

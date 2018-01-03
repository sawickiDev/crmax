package com.crmax.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;
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

    @Basic
    @Column(name = "start_date")
    private Date startDate;

    @Basic
    @Column(name = "end_date")
    private Date endDate;

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

    public Interaction(String stage, Date startDate, Date endDate) {
        this.stage = stage;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date lastDate) {
        this.endDate = lastDate;
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
                ", lastDate='" + endDate + '\'' +
                ", contactId=" + contactId +
                '}';
    }
}

package com.crmax.persistence.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @Column(name = "id")
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

    @ManyToMany(cascade = {CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "interactions_products_table",
                joinColumns = {@JoinColumn(name = "interaction_junction_id")},
                inverseJoinColumns = {@JoinColumn(name = "product_junction_id")})
    private List<Product> products = new ArrayList<>();

    @Transient
    private String productsSelected;

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getProductsSelected() {
        return productsSelected;
    }

    public void setProductsSelected(String productsSelected) {
        this.productsSelected = productsSelected;
    }

    @Override
    public String toString() {
        return "Interaction{" +
                "id=" + id +
                ", stage='" + stage + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", products=" + products +
                '}';
    }
}

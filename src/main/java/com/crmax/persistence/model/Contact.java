package com.crmax.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "contacts_table")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "First Name is required")
    @Size(max = 30, message = "First Name is too long")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(max = 30, message = "Last Name is too long")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Provide Email in valid format")
    @Column(name = "email")
    private String email;

    @Size(max = 20, message = "Phone is too long")
    @Column(name = "phone")
    private String phone;

    @Size(max = 30, message = "Company Name is too long")
    @Column(name = "company_name")
    private String companyName;

    @JoinColumn(name = "owner_id")
    @ManyToOne
    private User ownerId;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.DETACH, CascadeType.REFRESH},
                mappedBy = "contactId")
    private List<Interaction> interactions;


    public Contact() {
    }

    public Contact(Integer id, String firstName, String lastName, String email, String phone, String companyName, User ownerId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.companyName = companyName;
        this.ownerId = ownerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public User getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(User ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }
}

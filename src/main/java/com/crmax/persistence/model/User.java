package com.crmax.persistence.model;

import com.crmax.persistence.validation.PasswordNotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users_table")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty(message = "First Name is required")
    @Size(max = 30, message = "First Name is too long")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "First Name is required")
    @Size(max = 30, message = "First Name is too long")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email is required")
    @Email(message = "Provide Email in valid format")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Username is required")
    @Size(max = 30, message = "Username is too long")
    @Column(name = "username")
    private String username;

    @Size(max = 20, message = "Phone is too long")
    @Column(name = "phone")
    private String phoneNumber;

    @ManyToOne(cascade = {CascadeType.MERGE,
                            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "supervisor_id")
    private User supervisor;

    @OneToMany(mappedBy = "supervisor",
                cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH, CascadeType.REFRESH},
                fetch = FetchType.EAGER)
    private List<User> subordinates;

    @OneToOne(cascade = {CascadeType.MERGE,
                CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role roleId;

    @PasswordNotEmpty(message = "Password is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "password_id")
    private Password passwordId;

    @Transient
    private String supervisorCache;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.DETACH, CascadeType.REFRESH},
                mappedBy = "ownerId")
    private List<Contact> contacts;

    public User() {
    }

    public User(String firstName, String lastName, String email, String username, String phoneNumber, User supervisor, Role roleId, Password passwordId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.supervisor = supervisor;
        this.roleId = roleId;
        this.passwordId = passwordId;
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

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
    }

    public List<User> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<User> subordinates) {
        this.subordinates = subordinates;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public Password getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(Password passwordId) {
        this.passwordId = passwordId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getSupervisorCache() {
        return supervisorCache;
    }

    public void setSupervisorCache(String supervisorCache) {
        this.supervisorCache = supervisorCache;
    }

    public void addAdminUser(User subordinate) {

        if(subordinates == null)
            subordinates = new ArrayList<User>();
        else
            subordinates.add(subordinate);

        subordinate.setSupervisor(this);

    }

    public void addContact(Contact contact) {
        if(contacts == null)
            contacts = new ArrayList<Contact>();
        else
            contacts.add(contact);

        contact.setOwnerId(this);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(roleId.getRole()));

        return authorities;
    }

    public String getPassword() {
        System.out.println("password :: " + this.passwordId.getPassword());
        return this.passwordId.getPassword();
    }

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roleId=" + roleId +
                ", passwordId=" + passwordId +
                '}';
    }
}

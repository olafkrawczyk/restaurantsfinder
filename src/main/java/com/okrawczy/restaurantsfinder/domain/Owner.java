package com.okrawczy.restaurantsfinder.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Olaf on 2017-10-08.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Owner {

    @Id
    @SequenceGenerator(name="owner_generator", sequenceName="owner_sequence", initialValue = 10)
    @GeneratedValue(generator = "owner_generator")
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @Column(nullable = false)
    private String password;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "owner")
    private Collection<Restaurant> restaurants;

    public Owner() {
    }

    public Collection<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Collection<Restaurant> restaurants) {
        this.restaurants = restaurants;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return this.emailAddress;
    }

    public void setUsername(String email) {
        this.emailAddress = email;
    }
}

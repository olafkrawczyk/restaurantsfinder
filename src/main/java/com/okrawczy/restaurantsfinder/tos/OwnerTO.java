package com.okrawczy.restaurantsfinder.tos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;

/**
 * Created by Olaf on 2017-10-26.
 */
public class OwnerTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;

    public OwnerTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

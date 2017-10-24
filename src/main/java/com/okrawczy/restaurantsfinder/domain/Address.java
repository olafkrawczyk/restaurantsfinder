package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;

/**
 * Created by Olaf on 2017-10-08.
 */

@Entity
public class Address {

    @SequenceGenerator(name="address_generator", sequenceName="address_sequence", initialValue = 10)
    @GeneratedValue(generator = "address_generator")
    @Id
    private Long id;
    private String street;
    private String City;
    private String postalCode;

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}

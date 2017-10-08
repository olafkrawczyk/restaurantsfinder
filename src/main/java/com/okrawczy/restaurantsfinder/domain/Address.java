package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Olaf on 2017-10-08.
 */
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String City;
    private String postalCode;
    private String streetNumber;

}

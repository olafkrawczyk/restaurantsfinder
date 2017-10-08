package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Olaf on 2017-10-08.
 */

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Address address;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "restaurant")
    private Collection<Reservation> reservations;

    private String name;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;

    @OneToMany
    private Collection<RestaurantTable> tables;
}

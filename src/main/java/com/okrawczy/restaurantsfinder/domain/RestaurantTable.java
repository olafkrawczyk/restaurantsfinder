package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;

/**
 * Created by Olaf on 2017-10-08.
 */

@Entity
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private Integer seats;
}

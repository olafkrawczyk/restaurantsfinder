package com.okrawczy.restaurantsfinder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * Created by Olaf on 2017-10-08.
 */

@Entity
public class RestaurantTable {

    @SequenceGenerator(name = "table_generator", sequenceName = "table_sequence", initialValue = 10)
    @GeneratedValue(generator = "table_generator")
    @Id
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    private Integer seats;
    private String restaurantTableId;

    public RestaurantTable() {
    }

    public String getRestaurantTableId() {
        return restaurantTableId;
    }

    public void setRestaurantTableId(String restaurantTableId) {
        this.restaurantTableId = restaurantTableId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}

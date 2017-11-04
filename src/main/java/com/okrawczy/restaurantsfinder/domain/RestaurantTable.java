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

    public Long getId() {
        return id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RestaurantTable that = (RestaurantTable) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (restaurant != null ? !restaurant.equals(that.restaurant) : that.restaurant != null) return false;
        if (seats != null ? !seats.equals(that.seats) : that.seats != null) return false;
        return restaurantTableId != null ? restaurantTableId.equals(that.restaurantTableId) : that.restaurantTableId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (seats != null ? seats.hashCode() : 0);
        result = 31 * result + (restaurantTableId != null ? restaurantTableId.hashCode() : 0);
        return result;
    }
}

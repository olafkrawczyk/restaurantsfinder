package com.okrawczy.restaurantsfinder.tos;

/**
 * Created by Olaf on 2017-11-04.
 */
public class RestaurantTableTO {

    private Long id;
    private Long restaurantId;
    private Integer seats;
    private String restaurantTableId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getRestaurantTableId() {
        return restaurantTableId;
    }

    public void setRestaurantTableId(String restaurantTableId) {
        this.restaurantTableId = restaurantTableId;
    }
}

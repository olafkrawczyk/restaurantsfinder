package com.okrawczy.restaurantsfinder.tos;

import com.okrawczy.restaurantsfinder.domain.ReservationStatus;

import java.util.Date;

/**
 * Created by Olaf on 2017-11-06.
 */
public class ReservationTO {

    private Long id;
    private ReservationStatus status;
    private ClientTO client;
    private RestaurantStubTO restaurant;
    private Date reservationDate;
    private Date creationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public ClientTO getClient() {
        return client;
    }

    public void setClient(ClientTO client) {
        this.client = client;
    }

    public RestaurantStubTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantStubTO restaurant) {
        this.restaurant = restaurant;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}

package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Olaf on 2017-10-08.
 */

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "retaurant_id")
    private Restaurant restaurant;

    @OneToOne
    private RestaurantTable table;
    private Date reservationDate;
    private Date creationDate;
    private int reservationHours = 1;

    public Reservation() {
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getDate() {
        return reservationDate;
    }

    public void setDate(Date date) {
        this.reservationDate = date;
    }

    public int getReservationHours() {
        return reservationHours;
    }

    public void setReservationHours(int reservationHours) {
        this.reservationHours = reservationHours;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public RestaurantTable getTable() {
        return table;
    }

    public void setTable(RestaurantTable table) {
        this.table = table;
    }
}

package com.okrawczy.restaurantsfinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Olaf on 2017-10-08.
 */

@Entity
public class Reservation {

    public static int TIME_SLOT_DURATION = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "retaurant_id")
    private Restaurant restaurant;

    @OneToOne
    private RestaurantTable table;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date date) {
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

    public boolean isActive(){
        return reservationStatus.equals(ReservationStatus.ACCEPTED) || reservationStatus.equals(ReservationStatus.PENDING);
    }

    public boolean intersects(Date date) {
        long duration = ((long) Reservation.TIME_SLOT_DURATION) * 3600 *1000;
        return this.getReservationDate().getTime() <= date.getTime()+duration
                && date.getTime() <= this.getReservationDate().getTime() + duration;
    }

    public Long getId() {
        return id;
    }
}

package com.okrawczy.restaurantsfinder.tos;

import java.util.Date;

/**
 * Created by Olaf on 2017-11-05.
 */
public class ReservationRequest {

    private RestaurantTableTO restaurantTable;
    private Date reservationDate;

    public ReservationRequest(RestaurantTableTO restaurantTable, Date reservationDate) {
        this.restaurantTable = restaurantTable;
        this.reservationDate = reservationDate;
    }

    public RestaurantTableTO getRestaurantTable() {
        return restaurantTable;
    }

    public void setRestaurantTable(RestaurantTableTO restaurantTable) {
        this.restaurantTable = restaurantTable;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }
}

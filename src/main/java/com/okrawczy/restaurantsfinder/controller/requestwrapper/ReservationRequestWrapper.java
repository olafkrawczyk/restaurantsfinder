package com.okrawczy.restaurantsfinder.controller.requestwrapper;

/**
 * Created by Olaf on 2017-11-05.
 */

public class ReservationRequestWrapper {

    private String clientEmailAddress;
    private long restaurantId;
    private String reservationDateISO;
    private long tableId;

    public String getClientEmailAddress() {
        return clientEmailAddress;
    }

    public void setClientEmailAddress(String clientEmailAddress) {
        this.clientEmailAddress = clientEmailAddress;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getReservationDateISO() {
        return reservationDateISO;
    }

    public void setReservationDateISO(String reservationDateISO) {
        this.reservationDateISO = reservationDateISO;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        return "ReservationRequestWrapper{" +
                "emailAddress=" + clientEmailAddress +
                ", restaurantId=" + restaurantId +
                ", reservationDateISO='" + reservationDateISO + '\'' +
                ", tableId=" + tableId +
                '}';
    }
}

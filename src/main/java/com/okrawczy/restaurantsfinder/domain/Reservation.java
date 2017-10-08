package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Olaf on 2017-10-08.
 */
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "retaurant_id")
    private Restaurant restaurant;

    private Date date;
}

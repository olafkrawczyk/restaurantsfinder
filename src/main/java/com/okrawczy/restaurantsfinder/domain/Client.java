package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by Olaf on 2017-10-08.
 */

@Entity
public class Client extends Person {

    @OneToMany(mappedBy = "client")
    private Collection<Reservation> reservations;
}

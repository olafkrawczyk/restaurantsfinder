package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Olaf on 2017-10-08.
 */

@Entity
public class Owner extends Person {

    @OneToMany(mappedBy = "owner")
    private Collection<Restaurant> restaurants;

}

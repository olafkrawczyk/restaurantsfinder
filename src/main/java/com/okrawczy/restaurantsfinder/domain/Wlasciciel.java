package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Olaf on 2017-10-07.
 */
@Entity
public class Wlasciciel extends Osoba implements Serializable{

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "wlasciciel_restauracje",
    joinColumns = @JoinColumn(name = "wlasc_id"),
    inverseJoinColumns = @JoinColumn(name = "rest_id"))
    private Collection<Restauracja> restauracje;

    public Wlasciciel() {
        super();
    }

    public Collection<Restauracja> getRestauracje() {
        return restauracje;
    }

    public void setRestauracje(Collection<Restauracja> restauracje) {
        this.restauracje = restauracje;
    }
}

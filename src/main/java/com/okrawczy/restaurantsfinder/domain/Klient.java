package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.Entity;

/**
 * Created by Olaf on 2017-10-07.
 */
@Entity
public class Klient extends Osoba {
    private String telefon;

    public Klient() {
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}

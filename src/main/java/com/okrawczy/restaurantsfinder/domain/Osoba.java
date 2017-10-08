package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;

/**
 * Created by Olaf on 2017-10-07.
 */

@Entity
@Inheritance
public abstract class Osoba {

    @Id
    @SequenceGenerator(name="osoba_generator", sequenceName="osoba_sequence", initialValue = 28)
    @GeneratedValue(generator = "osoba_generator")
    protected Long id;
    protected String imie;
    protected String nazwisko;
    protected String email;
    @OneToOne
    protected Adres adres;

    public Osoba() {
    }

    public Osoba(String imie, String nazwisko, String email) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}

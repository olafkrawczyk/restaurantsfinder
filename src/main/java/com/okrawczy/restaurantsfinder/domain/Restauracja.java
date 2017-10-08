package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Olaf on 2017-10-07.
 */

@Entity
public class Restauracja {

    @Id
    @SequenceGenerator(name="restauracja_generator", sequenceName="restauracja_sequence", initialValue = 28)
    @GeneratedValue(generator = "restauracja_generator")
    private Long id;
    private String nazwa;
    private String kuchnia;
    @OneToOne
    private Adres adres;

    @ManyToMany(mappedBy = "restauracje")
    private Collection<Wlasciciel> wlasciele;

    public Restauracja() {
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getKuchnia() {
        return kuchnia;
    }

    public void setKuchnia(String kuchnia) {
        this.kuchnia = kuchnia;
    }

    public Collection<Wlasciciel> getWlasciele() {
        return wlasciele;
    }

    public void setWlasciele(Collection<Wlasciciel> wlasciele) {
        this.wlasciele = wlasciele;
    }
}

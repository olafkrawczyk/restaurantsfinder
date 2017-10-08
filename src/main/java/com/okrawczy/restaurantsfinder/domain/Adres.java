package com.okrawczy.restaurantsfinder.domain;


import javax.persistence.*;

/**
 * Created by Olaf on 2017-10-07.
 */

@Entity
public class Adres {

    @Id
    @SequenceGenerator(name="adres_generator", sequenceName="adres_sequence", initialValue = 28)
    @GeneratedValue(generator = "adres_generator")
    private Long id;
    private String ulica;
    private String nrDomu;
    private String nrMieszkania;
    private String miasto;

    public Adres() {
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public void setNrDomu(String nrDomu) {
        this.nrDomu = nrDomu;
    }

    public String getNrMieszkania() {
        return nrMieszkania;
    }

    public void setNrMieszkania(String nrMieszkania) {
        this.nrMieszkania = nrMieszkania;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {

        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

}

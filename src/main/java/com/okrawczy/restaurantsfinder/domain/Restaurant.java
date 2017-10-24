package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Olaf on 2017-10-08.
 */

@Entity
public class Restaurant {

    @SequenceGenerator(name="restaurant_generator", sequenceName="restaurant_sequence", initialValue = 10)
    @GeneratedValue(generator = "restaurant_generator")
    @Id
    private Long id;

    @OneToOne
    private Address address;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "restaurant")
    private Collection<Reservation> reservations;

    private String name;
    private String phoneNumber;
    private String openHour;
    private String closeHour;
    private String description;
    private String email;

    @Enumerated(EnumType.STRING)
    private Cuisine cuisine;

    @OneToMany(mappedBy = "restaurant")
    private Collection<RestaurantTable> tables;

    @OneToOne
    private Menu menu;

    public Restaurant() {
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Collection<RestaurantTable> getTables() {
        return tables;
    }

    public void setTables(Collection<RestaurantTable> tables) {
        this.tables = tables;
    }

    public String getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(String closeHour) {
        this.closeHour = closeHour;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

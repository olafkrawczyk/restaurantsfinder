package com.okrawczy.restaurantsfinder.tos;

import com.okrawczy.restaurantsfinder.domain.Address;
import com.okrawczy.restaurantsfinder.domain.Cuisine;
import com.okrawczy.restaurantsfinder.domain.MenuItem;
import com.okrawczy.restaurantsfinder.domain.RestaurantTable;

import java.util.List;

/**
 * Created by Olaf on 2017-10-21.
 */

public class RestaurantTO {
    private String name;
    private Cuisine cuisine;
    private String photo;
    private String description;
    private String openHour;
    private String closeHour;
    private String phone;
    private String email;
    private Address address;
    private List<RestaurantTable> tables;
    private List<MenuItem> menuItems;

    public RestaurantTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(String closeHour) {
        this.closeHour = closeHour;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<RestaurantTable> getTables() {
        return tables;
    }

    public void setTables(List<RestaurantTable> tables) {
        this.tables = tables;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public String toString() {
        return "RestaurantTO{" +
                "name='" + name + '\'' +
                ", cuisine=" + cuisine +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", openHour='" + openHour + '\'' +
                ", closeHour='" + closeHour + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", tables=" + tables +
                ", menuItems=" + menuItems +
                '}';
    }
}

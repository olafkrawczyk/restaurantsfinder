package com.okrawczy.restaurantsfinder.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Olaf on 2017-10-24.
 */

@Entity
public class Menu {

    @SequenceGenerator(name="menu_generator", sequenceName="menu_sequence", initialValue = 10)
    @GeneratedValue(generator = "menu_generator")
    @Id
    private long id;

    @OneToOne(mappedBy = "menu")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu")
    private List<MenuItem> menuItems;

    public Menu() {
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (id != menu.id) return false;
        if (restaurant != null ? !restaurant.equals(menu.restaurant) : menu.restaurant != null) return false;
        return menuItems != null ? menuItems.equals(menu.menuItems) : menu.menuItems == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (menuItems != null ? menuItems.hashCode() : 0);
        return result;
    }
}

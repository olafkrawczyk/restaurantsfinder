package com.okrawczy.restaurantsfinder.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Olaf on 2017-10-24.
 */

@Entity
public class MenuItem {

    @SequenceGenerator(name="menuitem_generator", sequenceName="menuitem_sequence", initialValue = 10)
    @GeneratedValue(generator = "menuitem_generator")
    @Id
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    private String dishName;
    private double price;
    private String description;

    public MenuItem() {
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        if (id != menuItem.id) return false;
        if (Double.compare(menuItem.price, price) != 0) return false;
        if (!menu.equals(menuItem.menu)) return false;
        if (!dishName.equals(menuItem.dishName)) return false;
        return description.equals(menuItem.description);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + dishName.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + description.hashCode();
        return result;
    }
}

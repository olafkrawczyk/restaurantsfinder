package com.okrawczy.restaurantsfinder.domain;

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
}

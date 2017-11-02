package com.okrawczy.restaurantsfinder.tos;

/**
 * Created by Olaf on 2017-10-25.
 */

public class RestaurantStubTO {

    private long id;
    private String name;
    private String description;
    private String imageURL;

    public RestaurantStubTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

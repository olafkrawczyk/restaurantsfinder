package com.okrawczy.restaurantsfinder.service.requestwrapper;

/**
 * Created by Olaf on 2017-11-02.
 */
public class RestaurantSearchParameters {
    private String city;
    private int people;
    private String date;
    private String time;
    private String cuisine;

    public RestaurantSearchParameters() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    @Override
    public String toString() {
        return "RestaurantSearchParameters{" +
                "city='" + city + '\'' +
                ", people=" + people +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cuisine='" + cuisine + '\'' +
                '}';
    }
}

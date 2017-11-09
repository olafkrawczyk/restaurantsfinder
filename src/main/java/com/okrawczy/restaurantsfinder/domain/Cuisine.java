package com.okrawczy.restaurantsfinder.domain;

/**
 * Created by Olaf on 2017-10-08.
 */
public enum Cuisine {
    FUSION("Fusion"), THAI("Thai"), ITALIAN("Italian"), POLISH("Polish"), FAST_FOOD("Fast food");

    public String redable;
    Cuisine(String b) {
        redable = b;
    }
}

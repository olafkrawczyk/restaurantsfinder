package com.okrawczy.restaurantsfinder.controller;

import com.okrawczy.restaurantsfinder.domain.Cuisine;
import com.okrawczy.restaurantsfinder.domain.Restaurant;
import com.okrawczy.restaurantsfinder.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Olaf on 2017-11-08.
 */

@RestController
public class HelperController {

    private final
    RestaurantRepository restaurantRepository;

    @Autowired
    public HelperController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @CrossOrigin
    @GetMapping("/restaurantsCities")
    public ResponseEntity<?> getRestaurantCities() {
        List<String> cities = new LinkedList<>();

        for (Restaurant restaurant : restaurantRepository.findAll()) {
            cities.add(restaurant.getAddress().getCity());
        }

        return ResponseEntity.ok(cities);
    }

    @CrossOrigin
    @GetMapping("/availableCuisines")
    public ResponseEntity<?> getAvailableCuisines() {
        List<List<String>> result = new LinkedList<>();

        for (Cuisine cuisine : Cuisine.values()) {
            List<String> temp = new LinkedList<>();
            temp.add(cuisine.toString());
            temp.add(cuisine.redable);
            result.add(temp);
        }
        return ResponseEntity.ok(result);
    }
}

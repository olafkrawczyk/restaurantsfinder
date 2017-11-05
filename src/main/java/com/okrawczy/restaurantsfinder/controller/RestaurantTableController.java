package com.okrawczy.restaurantsfinder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Olaf on 2017-11-03.
 */

@RestController
public class RestaurantTableController {

    @PostMapping("/tables/reserve/{id}")
    ResponseEntity<?> reserveTable(@RequestParam(value = "id") long id) {

        return ResponseEntity.ok("not implemented");
    }


}

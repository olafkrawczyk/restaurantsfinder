package com.okrawczy.restaurantsfinder.controller;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.domain.ReservationStatus;
import com.okrawczy.restaurantsfinder.domain.RestaurantTable;
import com.okrawczy.restaurantsfinder.repository.ReservationRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by Olaf on 2017-11-03.
 */

@RestController
public class RestaurantTableController {

    private static final Logger log = LoggerFactory.getLogger(RestaurantTableController.class);

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    @PostMapping("/tables/reserve/{id}")
    ResponseEntity<?> reserveTable(@PathVariable(value = "id") long id) {

        return ResponseEntity.ok("not implemented");
    }

    @PostMapping("/tables/delete/{id}")
    ResponseEntity<?> deleteTable(@PathVariable(value = "id") long id, Principal principal){

        RestaurantTable table = restaurantTableRepository.findTableById(id);

        if(!table.getRestaurant().getOwner().getUsername().equals(principal.getName()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only restaurant owner can delete table");

        List<Reservation> pending = reservationRepository.findReservationByTable_IdAndReservationStatus(id, ReservationStatus.PENDING);
        List<Reservation> accepted = reservationRepository.findReservationByTable_IdAndReservationStatus(id, ReservationStatus.ACCEPTED);

        if(!pending.isEmpty() || !accepted.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Cannot delete tables with active reservations (ACCEPTED, PENDING)");

        table.setDeleted(true);
        restaurantTableRepository.save(table);
        log.info("Table [" + id + "] deleted");

        return ResponseEntity.ok("Table [" + id + "] deleted");
    }

}

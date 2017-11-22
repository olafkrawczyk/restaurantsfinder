package com.okrawczy.restaurantsfinder.controller;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.domain.ReservationStatus;
import com.okrawczy.restaurantsfinder.domain.Restaurant;
import com.okrawczy.restaurantsfinder.domain.RestaurantTable;
import com.okrawczy.restaurantsfinder.repository.ReservationRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantTableRepository;
import com.okrawczy.restaurantsfinder.tos.RestaurantTableTO;
import com.okrawczy.restaurantsfinder.utils.converters.RestaurantTableToTOConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantTableToTOConverter restaurantTableToTOConverter;


    @PostMapping("/tables/reserve/{id}")
    public ResponseEntity<?> reserveTable(@PathVariable(value = "id") long id) {

        return ResponseEntity.ok("not implemented");
    }

    @PostMapping("/tables/delete/{id}")
    public ResponseEntity<?> deleteTable(@PathVariable(value = "id") long id, Principal principal){

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

    @PostMapping("/tables/add")
    public ResponseEntity<?> addTable(@RequestBody RestaurantTableTO table) {
        if(table.getRestaurantTableId().isEmpty() || table.getSeats() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Restaurant table ID and seats cannot be empty");
        }
        Restaurant restaurant = restaurantRepository.findRestaurantById(table.getRestaurantId());
        RestaurantTable restaurantTable = new RestaurantTable();
        restaurantTable.setRestaurantTableId(table.getRestaurantTableId());
        restaurantTable.setSeats(table.getSeats());
        restaurantTable.setRestaurant(restaurant);

        restaurantTableRepository.save(restaurantTable);
        return ResponseEntity.ok("Table saved");
    }

    @GetMapping("/tables/restaurant/{id}")
    public ResponseEntity<?> getTablesByRestaurant(@PathVariable(value = "id") Long id) {
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);
        List<RestaurantTable> tables = restaurant.getTables().stream().filter(e->!e.isDeleted()).collect(Collectors.toList());
        List<RestaurantTableTO> result = tables.stream().map(e->restaurantTableToTOConverter.convertToTO(e)).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

}

package com.okrawczy.restaurantsfinder.controller;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.domain.RestaurantTable;
import com.okrawczy.restaurantsfinder.repository.ReservationRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantTableRepository;
import com.okrawczy.restaurantsfinder.tos.RestaurantTableTO;
import com.okrawczy.restaurantsfinder.utils.converters.RestaurantTableToTOConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Olaf on 2017-11-03.
 */

@RestController
public class RestaurantTableController {

    private static final Logger logger = Logger.getLogger(RestaurantTableController.class);

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Autowired
    private RestaurantTableToTOConverter restaurantTableToTOConverter;




    @PostMapping("/tables/reserve/{id}")
    ResponseEntity<?> reserveTable(@RequestParam(value = "id") long id) {

        return ResponseEntity.ok("not implemented");
    }

    @CrossOrigin
    @GetMapping("/tables/getFreeTables")
    public ResponseEntity<?> getFreeTables(@RequestParam(value = "restaurantId") long restaurantId,
                                           @RequestParam(value = "seats") int seats,
                                           @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        logger.info("Restaurant id: " + restaurantId);
        logger.info("Seats: " + seats);
        logger.info("Date: " + date);

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date dateOpen = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        Date dateClose = calendar.getTime();

        List<Reservation> reservations = this.reservationRepository.
                findByReservationDateAfterAndReservationDateBeforeAndRestaurant_IdAndTable_Seats(dateOpen, dateClose, restaurantId, seats);

        List<RestaurantTable> restaurantTables = this.restaurantTableRepository.findByRestaurant_IdAndSeats(restaurantId, 2);
        List<RestaurantTable> reservedTables = reservations.stream().map(Reservation::getTable).collect(Collectors.toList());

        restaurantTables.removeAll(reservedTables);

        List<RestaurantTableTO> result = restaurantTables.stream().map(p -> restaurantTableToTOConverter.convertToTO(p)).collect(Collectors.toList());
        logger.info(restaurantTables);

        return ResponseEntity.ok(result);
    }
}

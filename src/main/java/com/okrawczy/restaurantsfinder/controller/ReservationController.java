package com.okrawczy.restaurantsfinder.controller;

import com.okrawczy.restaurantsfinder.domain.*;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import com.okrawczy.restaurantsfinder.repository.ReservationRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantRepository;
import com.okrawczy.restaurantsfinder.service.RestaurantTableService;
import com.okrawczy.restaurantsfinder.tos.ReservationRequest;
import com.okrawczy.restaurantsfinder.tos.RestaurantTableTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Olaf on 2017-10-09.
 */

@RestController
public class ReservationController {

    private static final Logger logger = Logger.getLogger(ReservationController.class);

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantTableService restaurantTableService;

    @PostMapping(value = "/reservations/addReservation")
    public ResponseEntity<?> addReservation(@RequestParam(value = "client_id") Long client_id,
                                     @RequestParam(value = "restaurant_id") Long restaurant_id,
                                     @RequestParam(value = "date") String date,
                                     @RequestParam(value = "reservationStatus")ReservationStatus reservationStatus) {

        //TODO sprawdzić działanie konwersji

        Date reservationDate = new Date();
        Date creationDate = new Date();

        Reservation reservation = new Reservation();
        Client client = this.clientRepository.findClientById(client_id);
        Restaurant restaurant = this.restaurantRepository.findRestaurantById(restaurant_id);
        reservation.setClient(client);
        reservation.setRestaurant(restaurant);
        reservation.setReservationStatus(reservationStatus);
        reservation.setDate(reservationDate);
        reservation.setCreationDate(creationDate);

        this.reservationRepository.save(reservation);
        System.out.println("----- DATA: data");


        return ResponseEntity.ok(client_id + " " + restaurant_id + " " + reservationDate + " ELO XD");
    }


    @CrossOrigin
    @GetMapping("/reservations/availableSlots")
    public ResponseEntity<?> getAvailableReservations(@RequestParam(value = "date") String reservationDateISO,
                                                      @RequestParam(value = "restaurantId") long restaurantId,
                                                      @RequestParam(value = "seats") int seats) {

        Date reservationDate = getDateFromISO(reservationDateISO);
        List<RestaurantTableTO> availableTables = restaurantTableService.getFreeTablesTOs(restaurantId, seats, reservationDate);

        List<ReservationRequest> requests = availableTables.stream().map(p -> new ReservationRequest(p, reservationDate)).collect(Collectors.toList());

        return ResponseEntity.ok(requests);
    }

    private static Date getDateFromISO(String dateISO) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(dateISO);
        return Date.from(Instant.from(accessor));
    }
}

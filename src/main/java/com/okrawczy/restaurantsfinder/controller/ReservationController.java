package com.okrawczy.restaurantsfinder.controller;

import com.okrawczy.restaurantsfinder.controller.requestwrapper.ReservationRequestWrapper;
import com.okrawczy.restaurantsfinder.domain.*;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import com.okrawczy.restaurantsfinder.repository.ReservationRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantTableRepository;
import com.okrawczy.restaurantsfinder.service.RestaurantTableService;
import com.okrawczy.restaurantsfinder.tos.ReservationRequest;
import com.okrawczy.restaurantsfinder.tos.ReservationTO;
import com.okrawczy.restaurantsfinder.tos.RestaurantTableTO;
import com.okrawczy.restaurantsfinder.utils.converters.ReservationTOConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private RestaurantTableRepository restaurantTableRepository;

    @Autowired
    private RestaurantTableService restaurantTableService;

    @Autowired
    private ReservationTOConverter reservationTOConverter;

    @CrossOrigin
    @PostMapping("/reservations/makeReservation")
    public ResponseEntity<?> makeReservation(@RequestBody ReservationRequestWrapper request) {
    
        

        Reservation reservation = new Reservation();
        Client client = clientRepository.findClientByEmailAddressIgnoreCase(request.getClientEmailAddress());
        Restaurant restaurant = restaurantRepository.findRestaurantById(request.getRestaurantId());
        Date reservationDate = getDateFromISO(request.getReservationDateISO());
        RestaurantTable table = restaurantTableRepository.findTableById(request.getTableId());

        boolean tableStillFree = restaurantTableService.getFreeTablesTOs(restaurant.getId(), table.getSeats(), reservationDate)
                .stream().anyMatch(t -> t.getId().equals(table.getId()));

        if (!tableStillFree) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Table already reserved. Please choose different one.");
        }
        
        reservation.setCreationDate(new Date());
        reservation.setClient(client);
        reservation.setRestaurant(restaurant);
        reservation.setReservationStatus(ReservationStatus.PENDING);
        reservation.setReservationDate(reservationDate);
        reservation.setTable(table);

        this.reservationRepository.save(reservation);

        return ResponseEntity.ok("Reserved");
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

    @CrossOrigin
    @GetMapping("/reservations/pendingReservations/{id}")
    public ResponseEntity<?> getPendingReservationsByRestaurantId(@PathVariable(value = "id") long id) {

        List<Reservation> pending = reservationRepository.findByReservationStatusAndRestaurant_Id(ReservationStatus.PENDING, id);
        List<ReservationTO> result = pending.stream().map(p -> reservationTOConverter.convertToTO(p)).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @CrossOrigin
    @GetMapping("/reservations/restaurant/{id}")
    public ResponseEntity<?> getReservationsByRestaurant(@PathVariable(value = "id") Long id) {
        List<Reservation> reservations = reservationRepository.findReservationsByRestaurant_Id(id);
        List<ReservationTO> result = reservations.stream()
                .map(p -> reservationTOConverter.convertToTO(p))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @CrossOrigin
    @PostMapping("/reservations/accept")
    public ResponseEntity<?> acceptReservation(@RequestParam(value = "reservationId") Long reservationId) {
        Reservation reservation = this.reservationRepository.findReservationById(reservationId);

        if (reservation.getReservationStatus().equals(ReservationStatus.REJECTED) ||
                reservation.getReservationStatus().equals(ReservationStatus.PENDING)) {

            reservation.setReservationStatus(ReservationStatus.ACCEPTED);
            reservationRepository.save(reservation);
            logger.info("Reservation [" + reservationId + "] accepted");

            return ResponseEntity.ok("Reservation " + reservationId + " accepted");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Reservation [" + reservation.getId() + "] status: "
                            + reservation.getReservationStatus() + " cannot be changed");
        }
    }

    @CrossOrigin
    @PostMapping("/reservations/reject")
    public ResponseEntity<?> rejectReservation(@RequestParam(value = "reservationId") Long reservationId) {
        Reservation reservation = this.reservationRepository.findReservationById(reservationId);

        if (reservation.getReservationStatus().equals(ReservationStatus.ACCEPTED) ||
                reservation.getReservationStatus().equals(ReservationStatus.PENDING)) {
            reservation.setReservationStatus(ReservationStatus.REJECTED);
            reservationRepository.save(reservation);
            logger.info("Reservation [" + reservationId + "] rejected");

            return ResponseEntity.ok("Reservation " + reservationId + " rejected");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Reservation Status: " + reservation.getReservationStatus() + " cannot be changed");
        }
    }

    @CrossOrigin
    @PostMapping("/reservations/cancel")
    public ResponseEntity<?> cancelReservation(@RequestParam(value = "reservationId") Long reservationId) {
        Reservation reservation = this.reservationRepository.findReservationById(reservationId);

        if (reservation.getReservationStatus().equals(ReservationStatus.CLOSED) ||
                reservation.getReservationStatus().equals(ReservationStatus.CANCELED) ||
                reservation.getReservationStatus().equals(ReservationStatus.REJECTED))
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Reservation Status: " + reservation.getReservationStatus() + " cannot be changed");

        reservation.setReservationStatus(ReservationStatus.CANCELED);
        reservationRepository.save(reservation);

        logger.info("Reservation [" + reservationId + "] cancelled");

        return ResponseEntity.ok("Reservation " + reservationId + " cancelled");
    }

    @CrossOrigin
    @GetMapping("/reservations/client/{id}")
    public ResponseEntity<?> getUserReservations(@PathVariable("id") long id) {
        List<Reservation> userReservations = this.reservationRepository.findReservationsByClientId(id);
        List<ReservationTO> reservations = userReservations.stream()
                .map(e -> reservationTOConverter.convertToTO(e))
                .collect(Collectors.toList());

        return ResponseEntity.ok(reservations);
    }

    private boolean checkIfAbleToChange(Reservation reservation) {
        return !reservation.getReservationStatus().equals(ReservationStatus.PENDING);
    }

    public static Date getDateFromISO(String dateISO) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        TemporalAccessor accessor = timeFormatter.parse(dateISO);
        return Date.from(Instant.from(accessor));
    }
}

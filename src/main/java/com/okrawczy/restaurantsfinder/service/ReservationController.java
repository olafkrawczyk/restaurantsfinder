package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.domain.Restaurant;
import com.okrawczy.restaurantsfinder.domain.ReservationStatus;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import com.okrawczy.restaurantsfinder.repository.ReservationRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * Created by Olaf on 2017-10-09.
 */

@RepositoryRestController
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(value = "/reservations/addReservation")
    ResponseEntity<?> addReservation(@RequestParam(value = "client_id") Long client_id,
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

}

package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.domain.ReservationStatus;
import com.okrawczy.restaurantsfinder.domain.RestaurantTable;
import com.okrawczy.restaurantsfinder.repository.ReservationRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantTableRepository;
import com.okrawczy.restaurantsfinder.tos.RestaurantTableTO;
import com.okrawczy.restaurantsfinder.utils.converters.RestaurantTableToTOConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Olaf on 2017-11-05.
 */

@Component
public class RestaurantTableService {

    private static final Logger logger = Logger.getLogger(RestaurantTableService.class);

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestaurantTableRepository restaurantTableRepository;

    @Autowired
    private RestaurantTableToTOConverter restaurantTableToTOConverter;

    public List<RestaurantTableTO> getFreeTablesTOs(long restaurantId, int seats, Date date) {


        logger.info("Restaurant id: " + restaurantId);
        logger.info("Seats: " + seats);
        logger.info("Date: " + date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date dateOpen = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        Date dateClose = calendar.getTime();

        List<Reservation> reservations = this.reservationRepository.
                findByReservationDateAfterAndReservationDateBeforeAndRestaurant_IdAndTable_Seats(dateOpen, dateClose, restaurantId, seats);

        List<RestaurantTable> restaurantTables = this.restaurantTableRepository.findByRestaurant_IdAndSeats(restaurantId, seats);
        List<RestaurantTable> reservedTables = reservations.stream().filter(Reservation::isReserved).map(Reservation::getTable).collect(Collectors.toList());

        restaurantTables.removeAll(reservedTables);

        List<RestaurantTableTO> result = restaurantTables.stream().map(p -> restaurantTableToTOConverter.convertToTO(p)).collect(Collectors.toList());
        logger.info(restaurantTables);

        return result;
    }
}

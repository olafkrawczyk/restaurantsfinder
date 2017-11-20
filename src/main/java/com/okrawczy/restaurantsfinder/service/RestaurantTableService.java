package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.domain.Restaurant;
import com.okrawczy.restaurantsfinder.domain.RestaurantTable;
import com.okrawczy.restaurantsfinder.repository.ReservationRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantTableRepository;
import com.okrawczy.restaurantsfinder.tos.RestaurantTableTO;
import com.okrawczy.restaurantsfinder.utils.converters.RestaurantTableToTOConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
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

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantTableTO> getFreeTablesTOs(long restaurantId, int seats, Date date) {

        Restaurant restaurant = restaurantRepository.findRestaurantById(restaurantId);
        int openHour = Integer.valueOf(restaurant.getOpenHour().substring(0, 2));
        int openMinutes = Integer.valueOf(restaurant.getOpenHour().substring(3, 5));

        int closeHour = Integer.valueOf(restaurant.getCloseHour().substring(0, 2));
        int closeMinutes = Integer.valueOf(restaurant.getCloseHour().substring(3, 5));

        logger.info(openHour + ":" + openMinutes + " - " + closeHour + ":" + closeMinutes);
        logger.info("Restaurant id: " + restaurantId);
        logger.info("Seats: " + seats);
        logger.info("Date: " + date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, openHour);
        calendar.set(Calendar.MINUTE, openMinutes);
        Date dateOpen = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, closeHour);
        calendar.set(Calendar.MINUTE, closeMinutes);
        Date dateClose = calendar.getTime();

        if (!isBetweenInclusive(dateOpen, dateClose, date)) {
            return new LinkedList<>();
        }

        List<Reservation> reservations = this.reservationRepository.
                findByReservationDateAfterAndReservationDateBeforeAndRestaurant_IdAndTable_Seats(dateOpen, dateClose, restaurantId, seats);

        List<RestaurantTable> restaurantTables = this.restaurantTableRepository.findByRestaurant_IdAndSeats(restaurantId, seats);
        List<RestaurantTable> reservedTables = reservations.stream()
                .filter(Reservation::isActive)
                .filter(e -> e.intersects(date))
                .map(Reservation::getTable).collect(Collectors.toList());

        restaurantTables.removeAll(reservedTables);

        List<RestaurantTableTO> result = restaurantTables.stream()
                .map(p -> restaurantTableToTOConverter.convertToTO(p))
                .collect(Collectors.toList());

        logger.info(restaurantTables);

        return result;
    }

    boolean isBetweenInclusive(Date start, Date end, Date target) {
        return !target.before(start) && !target.after(end);
    }
}

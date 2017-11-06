package com.okrawczy.restaurantsfinder.utils.converters;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.tos.ClientTO;
import com.okrawczy.restaurantsfinder.tos.ReservationTO;
import com.okrawczy.restaurantsfinder.tos.RestaurantTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Olaf on 2017-11-06.
 */

@Component
public class ReservationTOConverter implements DomainTOConverter<Reservation, ReservationTO>{

    @Autowired
    private RestaurantTOConverter restaurantTOConverter;

    @Autowired
    private ClientTOConverter clientTOConverter;


    @Override
    public ReservationTO convertToTO(Reservation entity) {

        ReservationTO result = new ReservationTO();
        RestaurantTO restaurantTO = restaurantTOConverter.convertToTO(entity.getRestaurant());
        ClientTO clientTO = clientTOConverter.convertToTO(entity.getClient());

        result.setId(entity.getId());
        result.setReservationDate(entity.getReservationDate());
        result.setCreationDate(entity.getReservationDate());
        result.setClient(clientTO);
        result.setRestaurant(restaurantTO);

        return result;
    }
}

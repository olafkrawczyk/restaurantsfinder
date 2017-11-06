package com.okrawczy.restaurantsfinder.utils.converters;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import com.okrawczy.restaurantsfinder.tos.ClientTO;
import com.okrawczy.restaurantsfinder.tos.ReservationTO;
import com.okrawczy.restaurantsfinder.tos.RestaurantStubTO;
import com.okrawczy.restaurantsfinder.tos.RestaurantTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Olaf on 2017-11-06.
 */

@Component
public class ReservationTOConverter implements DomainTOConverter<Reservation, ReservationTO>{

    @Autowired
    private RestaurantToStubConverter restaurantToStubConverter;

    @Autowired
    private ClientTOConverter clientTOConverter;

    @Autowired
    private RestaurantTableToTOConverter restaurantTableToTOConverter;


    @Override
    public ReservationTO convertToTO(Reservation entity) {

        ReservationTO result = new ReservationTO();
        RestaurantStubTO restaurantTO = restaurantToStubConverter.convertToStub(entity.getRestaurant());
        ClientTO clientTO = clientTOConverter.convertToTO(entity.getClient());

        result.setId(entity.getId());
        result.setStatus(entity.getReservationStatus());
        result.setReservationDate(entity.getReservationDate());
        result.setCreationDate(entity.getReservationDate());
        result.setClient(clientTO);
        result.setRestaurant(restaurantTO);
        result.setRestaurantTableTO(restaurantTableToTOConverter.convertToTO(entity.getTable()));

        return result;
    }
}

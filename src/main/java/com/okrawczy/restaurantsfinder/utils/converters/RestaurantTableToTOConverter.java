package com.okrawczy.restaurantsfinder.utils.converters;

import com.okrawczy.restaurantsfinder.domain.RestaurantTable;
import com.okrawczy.restaurantsfinder.tos.RestaurantTableTO;
import org.springframework.stereotype.Component;

/**
 * Created by Olaf on 2017-11-04.
 */

@Component
public class RestaurantTableToTOConverter implements DomainTOConverter<RestaurantTable, RestaurantTableTO> {


    @Override
    public RestaurantTableTO convertToTO(RestaurantTable entity) {
        RestaurantTableTO tableTo = new RestaurantTableTO();
        tableTo.setId(entity.getId());
        tableTo.setRestaurantTableId(entity.getRestaurantTableId());
        tableTo.setRestaurantId(entity.getRestaurant().getId());
        tableTo.setSeats(entity.getSeats());
        return tableTo;
    }
}

package com.okrawczy.restaurantsfinder.utils.converters;

import com.okrawczy.restaurantsfinder.domain.MenuItem;
import com.okrawczy.restaurantsfinder.domain.Restaurant;
import com.okrawczy.restaurantsfinder.domain.RestaurantTable;
import com.okrawczy.restaurantsfinder.tos.RestaurantTO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Olaf on 2017-10-28.
 */

@Component
public class RestaurantTOConverter implements DomainTOConverter<Restaurant, RestaurantTO>{

    @Override
    public RestaurantTO convertToTO(Restaurant entity) {

        RestaurantTO result = new RestaurantTO();
        result.setName(entity.getName());
        result.setDescription(entity.getDescription());
        result.setAddress(entity.getAddress());
        result.setMenuItems(entity.getMenu() != null ? entity.getMenu().getMenuItems() : null);
        result.setCloseHour(entity.getCloseHour());
        result.setOpenHour(entity.getOpenHour());
        result.setCuisine(entity.getCuisine());
        result.setEmail(entity.getEmail());
        result.setTables((List<RestaurantTable>) entity.getTables());
        result.setId(entity.getId());

        return result;
    }
}

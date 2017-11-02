package com.okrawczy.restaurantsfinder.utils.converters;

import com.okrawczy.restaurantsfinder.domain.Restaurant;
import com.okrawczy.restaurantsfinder.tos.RestaurantStubTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Olaf on 2017-10-25.
 */

@Component
public class RestaurantToStubConverter implements DomainStubConverter<Restaurant, RestaurantStubTO> {

    @Override
    public RestaurantStubTO convertToStub(Restaurant entity) {
        RestaurantStubTO stub = new RestaurantStubTO();
        stub.setId(entity.getId());
        stub.setName(entity.getName());
        stub.setDescription(entity.getDescription());
        stub.setImageURL(entity.getPhoto());

        return stub;
    }
}

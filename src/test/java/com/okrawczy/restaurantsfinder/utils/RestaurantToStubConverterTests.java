package com.okrawczy.restaurantsfinder.utils;

import com.okrawczy.restaurantsfinder.domain.Restaurant;
import com.okrawczy.restaurantsfinder.tos.RestaurantStubTO;
import com.okrawczy.restaurantsfinder.utils.converters.RestaurantToStubConverter;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Olaf on 2017-11-20.
 */


public class RestaurantToStubConverterTests {

    private static Restaurant restaurant;
    private static String name = "Name";
    private static String description = "Test example description";
    private static String photoURL = "http://example.com/example.jpg";
    private static Long id = Long.valueOf(10);

    @BeforeClass
    public static void setUpRestaurant() {
        restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setPhoto(photoURL);
        restaurant.setId(id);
    }

    @Test
    public void testConvertRestaurantToStub() {
        RestaurantToStubConverter restaurantToStubConverter = new RestaurantToStubConverter();
        RestaurantStubTO stub = restaurantToStubConverter.convertToStub(restaurant);

        assertEquals(stub.getName(), name);
        assertEquals(stub.getDescription(), description);
        assertEquals(stub.getImageURL(), photoURL);
        assertEquals(stub.getId(), (long) id);
    }

}

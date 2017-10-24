package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Address;
import com.okrawczy.restaurantsfinder.domain.Restaurant;
import com.okrawczy.restaurantsfinder.domain.RestaurantTable;
import com.okrawczy.restaurantsfinder.repository.AddressRepository;
import com.okrawczy.restaurantsfinder.repository.OwnerRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantRepository;
import com.okrawczy.restaurantsfinder.repository.RestaurantTableRepository;
import com.okrawczy.restaurantsfinder.tos.RestaurantTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Olaf on 2017-10-21.
 */

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @CrossOrigin
    @PostMapping("/restaurants/new")
    public ResponseEntity<?> newRestaurant(@RequestBody RestaurantTO restaurantRequest) {

        System.out.println(restaurantRequest);
        Address address = restaurantRequest.getAddress();
        addressRepository.save(address);

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setOwner(ownerRepository.findByEmailAddressIgnoreCase("john.doe@gmail.com"));
        restaurant.setCloseHour(restaurantRequest.getCloseHour());
        restaurant.setCuisine(restaurantRequest.getCuisine());
        restaurant.setDescription(restaurantRequest.getDescription());
        restaurant.setName(restaurantRequest.getName());
        restaurant.setEmail(restaurantRequest.getEmail());
        restaurant.setPhoneNumber(restaurantRequest.getPhone());
        restaurant.setOpenHour(restaurantRequest.getOpenHour());

        restaurantRepository.save(restaurant);

        for (RestaurantTable table: restaurantRequest.getTables()) {
            RestaurantTable newTable = new RestaurantTable();
            newTable.setRestaurant(restaurant);
            newTable.setSeats(table.getSeats());
            newTable.setRestaurantTableId(table.getRestaurantTableId());
            restaurantTableRepository.save(newTable);
        }

        return ResponseEntity.ok("Added");
    }
}

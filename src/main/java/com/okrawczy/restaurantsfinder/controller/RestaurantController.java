package com.okrawczy.restaurantsfinder.controller;

import com.okrawczy.restaurantsfinder.domain.*;
import com.okrawczy.restaurantsfinder.repository.*;
import com.okrawczy.restaurantsfinder.controller.requestwrapper.RestaurantSearchParameters;
import com.okrawczy.restaurantsfinder.tos.RestaurantTO;
import com.okrawczy.restaurantsfinder.utils.converters.RestaurantTOConverter;
import com.okrawczy.restaurantsfinder.utils.converters.RestaurantToStubConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Olaf on 2017-10-21.
 */

@RestController
public class RestaurantController {

    private static final Logger logger = Logger.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantTOConverter restaurantTOConverter;
    @Autowired
    private RestaurantToStubConverter restaurantToStubConverter;

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private RestaurantTableRepository restaurantTableRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @CrossOrigin
    @PostMapping("/restaurants/new")
    public ResponseEntity<?> newRestaurant(@RequestBody RestaurantTO restaurantRequest) {

        logger.debug(restaurantRequest);

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

        Menu menu = new Menu();
        menu.setRestaurant(restaurant);
        menuRepository.save(menu);
        restaurant.setMenu(menu);

        for(MenuItem menuItem: restaurantRequest.getMenuItems()) {
            menuItem.setMenu(menu);
            menuItemRepository.save(menuItem);
        }

        restaurantRepository.save(restaurant);
        return ResponseEntity.ok("Added");
    }

    @CrossOrigin
    @GetMapping("/restaurants/{id}")
    public ResponseEntity<?> getRestaurant(@PathVariable(value = "id") long id) {
        RestaurantTO restaurant = this.restaurantTOConverter.convertToTO(this.restaurantRepository.findRestaurantById(id));

        return ResponseEntity.ok(restaurant);
    }

    @CrossOrigin
    @PostMapping("/restaurants/findByParameters")
    public ResponseEntity<?> findByParameters(@RequestBody RestaurantSearchParameters searchParams) {
        logger.info(searchParams);
        List<RestaurantTO> results = this.restaurantRepository.findByAddress_CityIgnoreCase(searchParams.getCity())
                .stream().map(e -> restaurantTOConverter.convertToTO(e)).collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }
}

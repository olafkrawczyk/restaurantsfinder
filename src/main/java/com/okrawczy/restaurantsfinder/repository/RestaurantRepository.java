package com.okrawczy.restaurantsfinder.repository;

import com.okrawczy.restaurantsfinder.domain.Cuisine;
import com.okrawczy.restaurantsfinder.domain.Restaurant;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Olaf on 2017-10-09.
 */

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
    Restaurant findRestaurantById(Long id);
    List<Restaurant> findByAddress_CityIgnoreCase(String city);
    List<Restaurant> findByAddress_CityIgnoreCaseAndCuisine(String city, Cuisine cuisine);
}

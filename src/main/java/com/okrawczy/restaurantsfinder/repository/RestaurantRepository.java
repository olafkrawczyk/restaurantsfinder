package com.okrawczy.restaurantsfinder.repository;

import com.okrawczy.restaurantsfinder.domain.Restaurant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Olaf on 2017-10-09.
 */

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
    Restaurant findRestaurantById(Long id);
}

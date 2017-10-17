package com.okrawczy.restaurantsfinder.repository;

import com.okrawczy.restaurantsfinder.domain.RestaurantTable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Olaf on 2017-10-09.
 */

@RepositoryRestResource
public interface RestaurantTableRepository extends PagingAndSortingRepository<RestaurantTable, Long> {

}

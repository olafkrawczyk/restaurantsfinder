package com.okrawczy.restaurantsfinder.repository;

import com.okrawczy.restaurantsfinder.domain.Menu;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Olaf on 2017-10-24.
 */


public interface MenuRepository extends PagingAndSortingRepository<Menu, Long> {

}

package com.okrawczy.restaurantsfinder.repository;

import com.okrawczy.restaurantsfinder.domain.MenuItem;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olaf on 2017-10-24.
 */
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}

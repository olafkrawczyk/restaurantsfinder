package com.okrawczy.restaurantsfinder.repository;

import com.okrawczy.restaurantsfinder.domain.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Olaf on 2017-10-08.
 */

@RepositoryRestResource
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
}

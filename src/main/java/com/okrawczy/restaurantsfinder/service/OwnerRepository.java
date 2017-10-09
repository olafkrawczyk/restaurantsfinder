package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Owner;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Olaf on 2017-10-08.
 */
@RepositoryRestResource
public interface OwnerRepository extends PagingAndSortingRepository<Owner, Long> {
}

package com.okrawczy.restaurantsfinder.repository;

import com.okrawczy.restaurantsfinder.domain.Owner;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Olaf on 2017-10-08.
 */

public interface OwnerRepository extends PagingAndSortingRepository<Owner, Long> {
    Owner findByEmailAddressIgnoreCase(String emailAddress);

    List<Owner> findByEmailAddress(String emailAddress);
}

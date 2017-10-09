package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Olaf on 2017-10-09.
 */

@RepositoryRestResource
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

}

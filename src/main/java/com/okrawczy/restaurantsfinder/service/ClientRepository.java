package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Olaf on 2017-10-09.
 */

@RepositoryRestResource
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Client findClientById(Long id);
}

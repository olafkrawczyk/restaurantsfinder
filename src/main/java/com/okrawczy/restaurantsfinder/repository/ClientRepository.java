package com.okrawczy.restaurantsfinder.repository;

import com.okrawczy.restaurantsfinder.domain.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * Created by Olaf on 2017-10-09.
 */

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    Client findClientById(Long id);
    Client findClientByEmailAddressIgnoreCase(String emailAddress);
    List<Client> findByEmailAddress(String emailAddress);
}

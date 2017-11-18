package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * Created by Olaf on 2017-11-11.
 */


@Service
public class ClientDetailsServiceImpl implements UserDetailsService {
    private ClientRepository clientRepository;


    public ClientDetailsServiceImpl(ClientRepository applicationUserRepository) {
        this.clientRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client applicationUser = clientRepository.findClientByEmailAddressIgnoreCase(email);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList()) {
        };
    }
}
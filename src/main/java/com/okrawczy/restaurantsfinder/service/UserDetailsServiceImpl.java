package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Olaf on 2017-11-10.
 */


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ClientRepository clientRepository;

    public UserDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client applicationUser = clientRepository.findClientByEmailAddressIgnoreCase(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
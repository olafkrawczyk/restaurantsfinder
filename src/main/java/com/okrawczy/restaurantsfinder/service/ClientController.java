package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Olaf on 2017-10-16.
 */

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @CrossOrigin
    @PostMapping("/clients/new")
    public ResponseEntity<?> createNewUser(@RequestBody Client aClient)
    {
        System.out.println(aClient);
        if (!clientRepository.findByEmailAddress(aClient.getEmailAddress()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Client with given email address already exists");
        }
        clientRepository.save(aClient);
        return ResponseEntity.ok("Client created");
    }

}

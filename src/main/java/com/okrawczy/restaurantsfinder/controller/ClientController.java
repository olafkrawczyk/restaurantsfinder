package com.okrawczy.restaurantsfinder.controller;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import com.okrawczy.restaurantsfinder.controller.requestwrapper.CredentialsWrapper;
import com.okrawczy.restaurantsfinder.tos.ClientTO;
import com.okrawczy.restaurantsfinder.utils.converters.ClientTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Olaf on 2017-10-16.
 */

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientTOConverter clientTOConverter;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @CrossOrigin
    @PostMapping("/clients/new")
    public ResponseEntity<?> createNewClient(@RequestBody Client aClient)
    {
        if (!clientRepository.findByEmailAddress(aClient.getEmailAddress()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Client with given email address already exists");
        }

        if(aClient.getPassword().isEmpty() || aClient.getEmailAddress().isEmpty())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email and password are required");

        String password = aClient.getPassword();
        aClient.setPassword(bCryptPasswordEncoder.encode(password));

        clientRepository.save(aClient);
        return ResponseEntity.ok("Client created");
    }

    @CrossOrigin
    @GetMapping("/clients/getByEmail")
    public ResponseEntity<?> loginUser(@RequestParam(value = "email") String emailAddress){
        try {
            Client client = clientRepository.findClientByEmailAddressIgnoreCase(emailAddress);
            ClientTO clientTo = clientTOConverter.convertToTO(client);
            return ResponseEntity.ok(clientTo);
        }
        catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client with given email address not found");
        }
    }
}

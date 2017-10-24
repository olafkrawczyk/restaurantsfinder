package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.domain.Owner;
import com.okrawczy.restaurantsfinder.repository.OwnerRepository;
import com.okrawczy.restaurantsfinder.service.requestwrapper.CredentialsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Olaf on 2017-10-21.
 */

@RestController
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @CrossOrigin
    @PostMapping("/owners/new")
    public ResponseEntity<?> createNewOwner(@RequestBody Owner aOwner)
    {
        if (!ownerRepository.findByEmailAddress(aOwner.getEmailAddress()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Owner with given email address already exists");
        }
        ownerRepository.save(aOwner);
        return ResponseEntity.ok("Owner created");
    }

    @CrossOrigin
    @PostMapping("/owners/login")
    public ResponseEntity<?> loginOwner(@RequestBody CredentialsWrapper credentials ){
        try {
            Owner owner = ownerRepository.findByEmailAddressIgnoreCase(credentials.getEmailAddress());
            if (!owner.getPassword().equals(credentials.getPassword())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong password for owner " + owner.getEmailAddress());
            }
            else {
                return ResponseEntity.ok(owner);
            }
        }
        catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner with given email address not found");
        }
    }

}

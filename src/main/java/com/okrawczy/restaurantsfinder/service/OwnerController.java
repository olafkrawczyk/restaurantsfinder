package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.domain.Owner;
import com.okrawczy.restaurantsfinder.domain.Restaurant;
import com.okrawczy.restaurantsfinder.repository.OwnerRepository;
import com.okrawczy.restaurantsfinder.service.requestwrapper.CredentialsWrapper;
import com.okrawczy.restaurantsfinder.tos.OwnerTO;
import com.okrawczy.restaurantsfinder.tos.RestaurantStubTO;
import com.okrawczy.restaurantsfinder.utils.converters.RestaurantToStubConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Olaf on 2017-10-21.
 */

@RestController
public class OwnerController {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private RestaurantToStubConverter restaurantToStubConverter;

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
            OwnerTO ownerTO = new OwnerTO();
            ownerTO.setId(owner.getId());
            ownerTO.setFirstName(owner.getFirstName());
            ownerTO.setLastName(owner.getLastName());
            ownerTO.setEmailAddress(owner.getEmailAddress());
            if (!owner.getPassword().equals(credentials.getPassword())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wrong password for owner " + owner.getEmailAddress());
            }
            else {
                return ResponseEntity.ok(ownerTO);
            }
        }
        catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Owner with given email address not found");
        }
    }

    @CrossOrigin
    @GetMapping("/owners/{email}/restaurants/stubs")
    public ResponseEntity<List<RestaurantStubTO>> getOwnerRestaurants(@PathVariable(value = "email") String email) {
        Owner owner = this.ownerRepository.findByEmailAddressIgnoreCase(email);
        Collection<Restaurant> restaurants = owner.getRestaurants();
        List<RestaurantStubTO> result = restaurants.stream().map( e -> restaurantToStubConverter.convertToStub(e)).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

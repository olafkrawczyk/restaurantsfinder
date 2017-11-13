package com.okrawczy.restaurantsfinder;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.domain.Owner;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import com.okrawczy.restaurantsfinder.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Olaf on 2017-11-11.
 */

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        Client client = new Client();
        client.setEmailAddress("radeko94@gmail.com");
        client.setFirstName("Olaf");
        client.setLastName("Krawczyk");
        String password = bCryptPasswordEncoder.encode("password");
        client.setPassword(password);
        clientRepository.save(client);

        client = clientRepository.findClientByEmailAddressIgnoreCase("michalina.kowalska@gmail.com");
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        clientRepository.save(client);

        Owner owner = ownerRepository.findByEmailAddressIgnoreCase("john.doe@gmail.com");
        owner.setPassword(bCryptPasswordEncoder.encode("password"));
        ownerRepository.save(owner);
    }
}

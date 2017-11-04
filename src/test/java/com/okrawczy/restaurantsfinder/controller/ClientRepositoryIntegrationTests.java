package com.okrawczy.restaurantsfinder.controller;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.repository.ClientRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Olaf on 2017-10-09.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientRepositoryIntegrationTests {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void addCorrectClient() {
        String emailAddress = "john.doe@example.com";

        Client testClient = new Client();
        testClient.setFirstName("John");
        testClient.setLastName("Doe");
        testClient.setEmailAddress(emailAddress);
        testClient.setPassword("pass1234");

        Assert.assertNull(clientRepository.findClientByEmailAddressIgnoreCase(emailAddress));
        clientRepository.save(testClient);

        Client clientFromDB = clientRepository.findClientByEmailAddressIgnoreCase(emailAddress);
        Assert.assertEquals(testClient.getEmailAddress(), clientFromDB.getEmailAddress());
        clientRepository.delete(clientFromDB);
    }


    @Test(expected = DataIntegrityViolationException.class)
    public void addClientWithExistingEmail() {
        String emailAddress = "john.doe@example.com";

        Client testClient = new Client();
        testClient.setFirstName("John");
        testClient.setLastName("Doe");
        testClient.setEmailAddress(emailAddress);
        testClient.setPassword("pass1234");

        Client testClient2 = new Client();
        testClient2.setFirstName("John");
        testClient2.setLastName("Doe");
        testClient2.setEmailAddress(emailAddress);
        testClient2.setPassword("pass1234");

        clientRepository.save(testClient);

        clientRepository.save(testClient2);
        Assert.assertTrue(clientRepository.findByEmailAddress(emailAddress).size() == 1);
    }
}

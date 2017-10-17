package com.okrawczy.restaurantsfinder.service;

import com.okrawczy.restaurantsfinder.domain.Owner;
import com.okrawczy.restaurantsfinder.repository.OwnerRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Olaf on 2017-10-17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class OwnerRepositoryIntegrationTests {

    @Autowired
    private OwnerRepository ownerRepository;
    public static final String EMAIL_ADDRESS_IN_DATABASE = "john.doe@gmail.com";

    @Test
    public void getOwnerByEmailTest(){
        Owner owner = this.ownerRepository.findByEmailAddressIgnoreCase(EMAIL_ADDRESS_IN_DATABASE);
        Assert.assertEquals(EMAIL_ADDRESS_IN_DATABASE, owner.getEmailAddress());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void addOwnerWithoutEmailTest() {
        Owner o = new Owner();
        o.setFirstName("Jan");
        o.setLastName("Kowalski");

        this.ownerRepository.save(o);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void addOwnerWithExistingEmailAddress() {
        Owner o = new Owner();
        o.setEmailAddress(EMAIL_ADDRESS_IN_DATABASE);
        o.setFirstName("Jan");
        o.setLastName("Kowalski");

        this.ownerRepository.save(o);
    }
}

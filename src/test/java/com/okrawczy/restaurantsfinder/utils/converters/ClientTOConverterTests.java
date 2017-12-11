package com.okrawczy.restaurantsfinder.utils.converters;

import com.okrawczy.restaurantsfinder.domain.Client;
import com.okrawczy.restaurantsfinder.tos.ClientTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Olaf on 2017-12-07.
 */

public class ClientTOConverterTests {

    private ClientTOConverter converter = null;

    @Before
    public void setUpConverter() {
        this.converter = new ClientTOConverter();
    }

    @Test
    public void convertClinet() {
        Client client = new Client();
        client.setFirstName("John");
        client.setEmailAddress("jd@example.com");
        client.setPhoneNumber("12345");
        client.setLastName("Doe");

        ClientTO clientTO = this.converter.convertToTO(client);

        Assert.assertEquals(client.getFirstName(), clientTO.getFirstName());
        Assert.assertEquals(client.getLastName(), clientTO.getLastName());
        Assert.assertEquals(client.getEmailAddress(), clientTO.getEmailAddress());
        Assert.assertEquals(client.getPhoneNumber(), clientTO.getPhoneNumber());
    }
}

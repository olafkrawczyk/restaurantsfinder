package com.okrawczy.restaurantsfinder.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Olaf on 2017-11-21.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void registerClientTest() {
        try {
            mvc.perform(post("/clients/new").contentType(MediaType.APPLICATION_JSON).content("" +
                    "{\"firstName\": \"User1\"," +
                    "\"lastName\": \"LastName1\"," +
                    "\"emailAddress\": \"user@example.com\"," +
                    "\"password\": \"password\"," +
                    "\"phoneNumber\": \"0666015\"}")).andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void registerClientWithoutEmail() {

        try {
            mvc.perform(post("/clients/new").contentType(MediaType.APPLICATION_JSON).content("" +
                    "{\"firstName\": \"User1\"," +
                    "\"lastName\": \"LastName1\"," +
                    "\"emailAddress\": \"\"," +
                    "\"password\": \"password\"," +
                    "\"phoneNumber\": \"0666015\"}")).andExpect(status().is4xxClientError());
        } catch (Exception e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    //Test Login
}

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
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    @Test
    public void loginRegisteredUserCorrectCredentials() throws Exception {
        mvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content("" +
                "{\"username\": \"michalina.kowalska@gmail.com\"," +
                "\"password\": \"password\"}")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void loginRegisteredUserInvalidPassword() throws Exception {
        mvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content("" +
                "{\"username\": \"michalina.kowalska@gmail.com\"," +
                "\"password\": \"invalid\"}")).andExpect(status().is4xxClientError());
    }

    @Test
    public void getLoggedUserData() {
        try {
            MvcResult result = mvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content("" +
                    "{\"username\": \"michalina.kowalska@gmail.com\"," +
                    "\"password\": \"password\"}")).andReturn();
            String token = result.getResponse().getHeader("Authorization");
            Assert.assertNotNull(token);

            mvc.perform(get("/clients/getByEmail")
                    .header("Authorization", token).param("email", "michalina.kowalska@gmail.com"))
                    .andExpect(status().is2xxSuccessful());
        } catch (Exception e) {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }
}

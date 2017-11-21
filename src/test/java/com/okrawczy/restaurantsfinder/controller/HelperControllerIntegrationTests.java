package com.okrawczy.restaurantsfinder.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * Created by Olaf on 2017-11-20.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelperControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllCitiestTest() {
        try {
            mvc.perform(get("/restaurantsCities")).andDo(print())
                    .andExpect(content().json("[\"Wroclaw\",\"Warszawa\",\"Katowice\"]"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

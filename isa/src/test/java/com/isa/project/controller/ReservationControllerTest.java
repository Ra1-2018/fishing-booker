package com.isa.project.controller;

import com.isa.project.constants.ReservationConstants;
import com.isa.project.model.Reservation;
import com.isa.project.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.isa.project.constants.ReservationConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationControllerTest {

    private static final String URL_PREFIX = "/reservations";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "client@gmail.com", roles = {"USER", "CLIENT"})
    public void testFindByClient() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/client/" + CLIENT_ID)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].durationInDays").value(hasItem(DB_DURATION)))
                .andExpect(jsonPath("$.[*].numberOfPeople").value(hasItem(DB_NUMBER_OF_PEOPLE)))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
                .andExpect(jsonPath("$.[*].reservationStartDateAndTime").value(hasItem(DB_START_DATE.getTime())))
                .andExpect(jsonPath("$.[*].client.id").value(hasItem(CLIENT_ID.intValue())));
    }

    @Test
    @WithMockUser(username = "client@gmail.com", roles = {"USER", "CLIENT"})
    @Transactional
    @Rollback(true)
    public void testSave() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setService(DB_COTTAGE);
        reservation.setClient(DB_CLIENT);
        reservation.setPrice(NEW_PRICE);
        reservation.setReservationStartDateAndTime(NEW_START_DATE);
        reservation.setNumberOfPeople(NEW_NUMBER_OF_PEOPLE);
        reservation.setDurationInDays(NEW_DURATION);

        String json = TestUtil.json(reservation);
        this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "client@gmail.com", roles = {"USER", "CLIENT"})
    @Transactional
    @Rollback(true)
    public void testCancelReservation() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX + "/cancel/" + DB_ID)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "client@gmail.com", roles = {"USER", "CLIENT"})
    public void testFindByClientUpcoming() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/client-upcoming/" + CLIENT_ID)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].durationInDays").value(hasItem(DB_DURATION)))
                .andExpect(jsonPath("$.[*].numberOfPeople").value(hasItem(DB_NUMBER_OF_PEOPLE)))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
                .andExpect(jsonPath("$.[*].reservationStartDateAndTime").value(hasItem(DB_START_DATE.getTime())))
                .andExpect(jsonPath("$.[*].client.id").value(hasItem(CLIENT_ID.intValue())));
    }

    @Test
    @WithMockUser(username = "client@gmail.com", roles = {"USER", "CLIENT"})
    public void testFindByClientCottages() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/client-cottages/" + CLIENT_ID)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].durationInDays").value(hasItem(DB_DURATION)))
                .andExpect(jsonPath("$.[*].numberOfPeople").value(hasItem(DB_NUMBER_OF_PEOPLE)))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
                .andExpect(jsonPath("$.[*].reservationStartDateAndTime").value(hasItem(DB_START_DATE.getTime())))
                .andExpect(jsonPath("$.[*].client.id").value(hasItem(CLIENT_ID.intValue())));
    }
}

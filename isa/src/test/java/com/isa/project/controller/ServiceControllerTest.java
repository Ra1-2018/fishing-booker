package com.isa.project.controller;

import com.isa.project.dto.ServiceCriteriaDTO;
import com.isa.project.model.Reservation;
import com.isa.project.model.ServiceType;
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

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.isa.project.constants.ReservationConstants.*;
import static com.isa.project.constants.ServiceConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceControllerTest {

    private static final String URL_PREFIX = "/services";

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
    public void testFindServicesFromReservations() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/eligible/" + CLIENT_ID)).andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_COTTAGE_ID.intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DB_COTTAGE_DESCRIPTION)))
                .andExpect(jsonPath("$.[*].behaviorRules").value(hasItem(DB_COTTAGE_BEHAVIOR_RULES)))
                .andExpect(jsonPath("$.[*].pricePerDay").value(hasItem(DB_COTTAGE_PRICE_PER_DAY)))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DB_COTTAGE_ADDRESS)))
                .andExpect(jsonPath("$.[*].maxNumberOfPeople").value(hasItem(DB_COTTAGE_NUMBER_OF_PEOPLE)));

    }

    @Test
    @WithMockUser(username = "client@gmail.com", roles = {"USER", "CLIENT"})
    public void testGetSubscriptions() throws Exception{
        mockMvc.perform(get(URL_PREFIX + "/subscriptions/" + CLIENT_ID)).andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_COTTAGE_ID.intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DB_COTTAGE_DESCRIPTION)))
                .andExpect(jsonPath("$.[*].behaviorRules").value(hasItem(DB_COTTAGE_BEHAVIOR_RULES)))
                .andExpect(jsonPath("$.[*].pricePerDay").value(hasItem(DB_COTTAGE_PRICE_PER_DAY)))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DB_COTTAGE_ADDRESS)))
                .andExpect(jsonPath("$.[*].maxNumberOfPeople").value(hasItem(DB_COTTAGE_NUMBER_OF_PEOPLE)));
    }

    @Test
    @WithMockUser(username = "client@gmail.com", roles = {"USER", "CLIENT"})
    public void testGetServicesForCriteria() throws Exception {
        ServiceCriteriaDTO criteria = new ServiceCriteriaDTO(ServiceType.COTTAGE, new GregorianCalendar(2022, Calendar.MAY, 5).getTime(), 3, "", 3, 0);
        String json = TestUtil.json(criteria);

        mockMvc.perform(post(URL_PREFIX + "/search").contentType(contentType).content(json)).andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_COTTAGE_ID.intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DB_COTTAGE_DESCRIPTION)))
                .andExpect(jsonPath("$.[*].behaviorRules").value(hasItem(DB_COTTAGE_BEHAVIOR_RULES)))
                .andExpect(jsonPath("$.[*].pricePerDay").value(hasItem(DB_COTTAGE_PRICE_PER_DAY)))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DB_COTTAGE_ADDRESS)))
                .andExpect(jsonPath("$.[*].maxNumberOfPeople").value(hasItem(DB_COTTAGE_NUMBER_OF_PEOPLE)));
    }

    @Test
    @WithMockUser(username = "client@gmail.com", roles = {"USER", "CLIENT"})
    @Transactional
    @Rollback(true)
    public void testSubscribeToService() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/subscribe/" +DB_COTTAGE_ID + "/" + CLIENT_ID)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "client@gmail.com", roles = {"USER", "CLIENT"})
    @Transactional
    @Rollback(true)
    public void testUnsubscribeFromService() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/unsubscribe/" +DB_COTTAGE_ID + "/" + CLIENT_ID)).andExpect(status().isOk());
    }
}

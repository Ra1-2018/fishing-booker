package com.isa.project.controller;

import com.isa.project.model.Action;
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

import static com.isa.project.constants.ActionConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActionControllerTest {

    private static final String URL_PREFIX = "/actions";

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
    @WithMockUser(username = "nikola.iv.99@gmail.com", roles = {"USER", "CLIENT"})
    public void testFindByService() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/service/" + SERVICE_ID)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].durationInDays").value(hasItem(DB_DURATION)))
                .andExpect(jsonPath("$.[*].maxNumberOfPeople").value(hasItem(DB_NUMBER_OF_PEOPLE)))
                .andExpect(jsonPath("$.[*].price").value(hasItem(DB_PRICE)))
                .andExpect(jsonPath("$.[*].startTime").value(hasItem(DB_START_DATE.getTime())));
    }

    @Test
    @WithMockUser(username = "mila@gmail.com", roles = {"USER", "BOAT_OWNER"})
    @Transactional
    @Rollback(true)
    public void testCreateAction() throws Exception {
        Action action = new Action();
        action.setMaxNumberOfPeople(NEW_NUMBER_OF_PEOPLE);
        action.setStartTime(NEW_START_DATE);
        action.setDurationInDays(NEW_DURATION);
        action.setPrice(NEW_PRICE);
        action.setService(DB_BOAT);

        String json = TestUtil.json(action);
        this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "nikola.iv.99@gmail.com", roles = {"USER", "CLIENT"})
    @Transactional
    @Rollback(true)
    public void testMakeReservationFromAction() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX + "/reservation/" + CLIENT_ID + "/" + DB_ID) ).andExpect(status().isOk());
    }
}

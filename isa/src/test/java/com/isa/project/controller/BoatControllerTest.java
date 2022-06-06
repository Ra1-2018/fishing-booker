package com.isa.project.controller;

import com.isa.project.model.Boat;
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

import static com.isa.project.constants.BoatConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoatControllerTest {

    private static final String URL_PREFIX = "/boats";

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
    public void getBoatsTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DB_DESCRIPTION)))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
                .andExpect(jsonPath("$.[*].behaviorRules").value(hasItem(DB_BEHAVIOR_RULES)))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DB_TYPE)))
                .andExpect(jsonPath("$.[*].length").value(hasItem(DB_LENGTH)))
                .andExpect(jsonPath("$.[*].enginePower").value(hasItem(DB_ENGINE_POWER)))
                .andExpect(jsonPath("$.[*].maximumVelocity").value(hasItem(DB_MAXIMUM_VELOCITY)));

    }

    @Test
    @WithMockUser(username = "nikola.iv.99@gmail.com", roles = {"USER", "CLIENT"})
    public void getBoatTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/" + DB_ID)).andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(DB_DESCRIPTION))
                .andExpect(jsonPath("$.name").value(DB_NAME))
                .andExpect(jsonPath("$.behaviorRules").value(DB_BEHAVIOR_RULES))
                .andExpect(jsonPath("$.type").value(DB_TYPE))
                .andExpect(jsonPath("$.length").value(DB_LENGTH))
                .andExpect(jsonPath("$.enginePower").value(DB_ENGINE_POWER))
                .andExpect(jsonPath("$.maximumVelocity").value(DB_MAXIMUM_VELOCITY));
    }

    @Test
    @WithMockUser(username = "mila@gmail.com", roles = {"USER", "BOAT_OWNER"})
    public void getOwnerBoatsTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX + "/owner/" + DB_OWNER_ID)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID.intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DB_DESCRIPTION)))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DB_NAME)))
                .andExpect(jsonPath("$.[*].behaviorRules").value(hasItem(DB_BEHAVIOR_RULES)))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DB_TYPE)))
                .andExpect(jsonPath("$.[*].length").value(hasItem(DB_LENGTH)))
                .andExpect(jsonPath("$.[*].enginePower").value(hasItem(DB_ENGINE_POWER)))
                .andExpect(jsonPath("$.[*].maximumVelocity").value(hasItem(DB_MAXIMUM_VELOCITY)));
    }

    @Test
    @WithMockUser(username = "mila@gmail.com", roles = {"USER", "BOAT_OWNER"})
    @Transactional
    @Rollback(true)
    public void deleteBoatTest() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX + "/delete/" + DB_ID)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "mila@gmail.com", roles = {"USER", "BOAT_OWNER"})
    @Transactional
    @Rollback(true)
    public void createBoatTest() throws Exception {
        Boat boat = new Boat();
        boat.setBehaviorRules(NEW_DB_BEHAVIOR_RULES);
        boat.setName(NEW_DB_NAME);
        boat.setDescription(NEW_DB_DESCRIPTION);
        boat.setMaxNumberOfPeople(DB_MAX_NUMBER_OF_PEOPLE);
        boat.setEnginePower(DB_ENGINE_POWER);
        boat.setCancellationTerms(DB_CANCELLATION_TERMS);
        boat.setFishingEquipment(DB_FISHING_EQUIPMENT);
        boat.setLength(NEW_DB_LENGTH);
        boat.setNavigationEquipment(DB_NAVIGATION_EQUIPMENT);
        boat.setNumberOfEngines(DB_NUMBER_OF_ENGINES);
        boat.setType(DB_TYPE);
        boat.setMaximumVelocity(DB_MAXIMUM_VELOCITY);
        boat.setPricePerDay(NEW_DB_PRICE_PER_DAY);
        boat.setServiceType(ServiceType.BOAT);


        String json = TestUtil.json(boat);
        this.mockMvc.perform(post(URL_PREFIX + "/" + DB_OWNER_ID).contentType(contentType).content(json)).andExpect(status().isCreated());
    }
}

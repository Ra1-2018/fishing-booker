package com.isa.project.controller;

import com.isa.project.dto.TimeRangeDTO;
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

import static com.isa.project.constants.ActionConstants.CLIENT_ID;
import static com.isa.project.constants.ActionConstants.DB_ID;
import static com.isa.project.constants.TimRangeConstants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeRangeControllerTest {

    private static final String URL_PREFIX = "/timeRanges";

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
    @WithMockUser(username = "mila@gmail.com", roles = {"USER", "BOAT_OWNER"})
    @Transactional
    @Rollback(true)
    public void createFreePeriodTest() throws Exception {
        TimeRangeDTO timeRangeDTO = new TimeRangeDTO();
        timeRangeDTO.setStartDate(NEW_START_DATE);
        timeRangeDTO.setEndDate(NEW_END_DATE);
        timeRangeDTO.setServiceId(DB_SERVICE_ID);

        String json = TestUtil.json(timeRangeDTO);
        this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "mila@gmail.com", roles = {"USER", "BOAT_OWNER"})
    @Transactional
    @Rollback(true)
    public void deleteFreePeriodTest() throws Exception {
        this.mockMvc.perform(get(URL_PREFIX + "/delete/" + DB_ID)).andExpect(status().isOk());
    }
}

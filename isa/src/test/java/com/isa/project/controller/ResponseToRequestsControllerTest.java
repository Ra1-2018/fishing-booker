package com.isa.project.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.isa.project.constants.ResponseToRequestsConstants.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseToRequestsControllerTest {

    private static final String URL_PREFIX_DELETION = "/users/deletionRequests";
    private static final String URL_PREFIX_COMPLAINTS = "/users/complaints";

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
    @WithMockUser(username = "lenkaisidora.aleksic@gmail.com", roles = {"USER", "ADMIN"})
    public void getDeletionRequestsTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX_DELETION)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_REQUEST_ID.intValue())))
                .andExpect(jsonPath("$.[*].userEmail").value(hasItem(DB_REQUEST_USER_EMAIL)))
                .andExpect(jsonPath("$.[*].explanation").value(hasItem(DB_REQUEST_EXPLANATION)))
                .andExpect(jsonPath("$.[*].dateSubmitted").value(hasItem(DB_REQUEST_UPDATE_DATE)));
    }

    @Test
    @WithMockUser(username = "lenkaisidora.aleksic@gmail.com", roles = {"USER", "ADMIN"})
    public void getComplaintsTest() throws Exception {
        mockMvc.perform(get(URL_PREFIX_COMPLAINTS)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType)).andExpect(jsonPath("$", hasSize(DB_COUNT)))
                .andExpect(jsonPath("$.[*].id").value(hasItem(DB_COMPLAINT_ID.intValue())))
                .andExpect(jsonPath("$.[*].content").value(hasItem(DB_COMPLAINT_CONTENT)))
                .andExpect(jsonPath("$.[*].dateSubmitted").value(hasItem(DB_COMPLAINT_UPDATE_DATE)));
    }
}

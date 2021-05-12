package com.forge.revature.demo;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.forge.revature.controllers.WorkExperienceController;
import com.forge.revature.models.WorkExperience;
import com.forge.revature.repo.WorkExperienceRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class WorkExperienceTest {

    private MockMvc mock;

    @MockBean
    WorkExperienceRepo repo;

    @BeforeEach
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(new WorkExperienceController(repo)).build();
    }
    
    @Test
    void testGetAll() {
        given(repo.getAll()).willReturn(new ArrayList<WorkExperience>());

        mock.perform(get("/workexperience"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testGetById() {
        given(repo.findById(1)).willReturn(new WorkExperience("Walmart", "Software developer", "sample responsibilities",
                "sample description", "sample technologies", new SimpleDateFormat("yyyy-MM-dd").parse("2017-08-28"),
                new SimpleDateFormat("yyyy-MM-dd").parse("2020-02-07")));

        mock.perform(get("/workexperience/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }
}

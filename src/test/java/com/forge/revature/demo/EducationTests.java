package com.forge.revature.demo;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import com.forge.revature.controllers.EducationController;
import com.forge.revature.models.Education;
import com.forge.revature.repo.EducationRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
public class EducationTests {
    private MockMvc mockMvc;

    @MockBean
    EducationRepo educationRepo;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new EducationController(educationRepo)).build();
    }

    @Test
    void testGetAll() throws Exception {
        given(this.educationRepo.findAll()).willReturn(new ArrayList<Education>());

        this.mockMvc.perform(get("/aboutMe"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testGetById() throws Exception {
        given(this.educationRepo.findById(1)).willReturn(Optional.of(new Education()));

        this.mockMvc.perform(get("/aboutMe/1"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    //needs to be refined once access to Portfolio is gained
    /*
    @Test
    void testGetByPortfolioId() throws Exception {
        //given(this.educationRepo.findById(1)).willReturn(Optional.of(new Education()));

        this.mockMvc.perform(get("/aboutMe/1"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }
    */
}

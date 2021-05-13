package com.forge.revature.demo;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private Education testEducation;
    
    @MockBean
    EducationRepo educationRepo;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new EducationController(educationRepo)).build();
        this.testEducation = new Education("university", "degree", "graduationDate", 3.5);
    }

    @Test
    void testGetAll() throws Exception {
        given(this.educationRepo.findAll()).willReturn(new ArrayList<Education>());

        this.mockMvc.perform(get("/education"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testGetById() throws Exception {
        given(this.educationRepo.findById(1)).willReturn(Optional.of(testEducation));

        this.mockMvc.perform(get("/education/1"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testPostById() throws Exception {
        given(this.educationRepo.findById(1)).willReturn(Optional.of(testEducation));

        Education newEducation = new Education("Not a university", "Bachelor's", "2018", 3.5);

        this.mockMvc.perform(post("/education/1")
            .contentType("application/json;charset=utf-8")
            .content(new ObjectMapper().writeValueAsString(newEducation)))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    void testDelete() throws Exception {
        given(this.educationRepo.findById(1)).willReturn(Optional.of(testEducation));

        this.mockMvc.perform(delete("/education/1"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

        this.mockMvc.perform(get("/education/1"))
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

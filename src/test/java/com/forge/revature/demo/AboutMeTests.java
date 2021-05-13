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
import com.forge.revature.controllers.AboutMeController;
import com.forge.revature.models.AboutMe;
import com.forge.revature.repo.AboutMeRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
public class AboutMeTests {
    private MockMvc mockMvc;
    private AboutMe testAboutMe;

    @MockBean
    AboutMeRepo aboutMeRepo;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AboutMeController(aboutMeRepo)).build();
        this.testAboutMe = new AboutMe("Hi I'm Max", "max@mail.net", "(333) 333-4444");
    }

    @Test
    void testGetAll() throws Exception {
        given(this.aboutMeRepo.findAll()).willReturn(new ArrayList<AboutMe>());

        this.mockMvc.perform(get("/aboutMe"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testGetById() throws Exception {
        given(this.aboutMeRepo.findById(1)).willReturn(Optional.of(testAboutMe));

        this.mockMvc.perform(get("/aboutMe/1"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testPostById() throws Exception {
        given(this.aboutMeRepo.findById(1)).willReturn(Optional.of(testAboutMe));

        AboutMe newAboutMe = new AboutMe("Hi I'm not Max", "maxUpdated@mail.net", "(222) 333-4444");

        this.mockMvc.perform(post("/aboutMe/1")
            .contentType("application/json;charset=utf-8")
            .content(new ObjectMapper().writeValueAsString(newAboutMe)))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andReturn();
    }

    @Test
    void testDelete() throws Exception {
        given(this.aboutMeRepo.findById(1)).willReturn(Optional.of(testAboutMe));

        this.mockMvc.perform(delete("/aboutMe/1"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();

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
        given(this.aboutMeRepo.findByPortfolioId(1)).willReturn(Optional.of(new AboutMe("Hi I'm Max", "max@mail.net", "(333) 333-4444")));

        this.mockMvc.perform(get("/aboutMe/portfolio/1"))
            .andExpect(status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }
    */
}

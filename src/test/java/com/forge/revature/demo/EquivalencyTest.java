package com.forge.revature.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forge.revature.controllers.EquivalencyController;
import com.forge.revature.models.Equivalency;
import com.forge.revature.models.Portfolio;
import com.forge.revature.models.User;
import com.forge.revature.repo.EquivalencyRepo;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class EquivalencyTest {
    private MockMvc mvc;

    @MockBean
    EquivalencyRepo repo;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .standaloneSetup(new EquivalencyController(repo))
            .build();
    }

    @Test
    void testGetall() throws Exception {
        System.out.println(repo);
        given(repo.findAll())
            .willReturn(new ArrayList<Equivalency>());

        mvc.perform(get("/equiv"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testGetById() throws Exception{
        given(repo.findById(1)).willReturn(Optional.of(new Equivalency()));


        mvc.perform(get("/equiv/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testPost() throws Exception{
        Equivalency port = new Equivalency(1, "testheader", 3  ,new Portfolio(1, "new portfilio", new User(1, "test user", "password", false), false, false, false, ""));
            
        
        given(repo.save(port)).willReturn(port);

        mvc.perform(post("/equiv")
            .contentType("application/json;charset=utf-8")
            .content(new ObjectMapper().writeValueAsString(port)))

            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andReturn();
    }
}

package com.forge.revature.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forge.revature.controllers.PortfolioController;
import com.forge.revature.models.Portfolio;
import com.forge.revature.models.User;
import com.forge.revature.repo.PortfolioRepo;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
public class PortfolioTest {
    private MockMvc mvc;

    @MockBean
    PortfolioRepo repo;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .standaloneSetup(new PortfolioController(repo))
            .build();
    }

    @Test
    void testGetall() throws Exception {
        System.out.println(repo);
        given(repo.findAll())
            .willReturn(new ArrayList<Portfolio>());

        mvc.perform(get("/portfolios"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testGetById() throws Exception{
        given(repo.findById(1)).willReturn(Optional.of(new Portfolio()));


        mvc.perform(get("/portfolios/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }
    @Test
    void testGetAllByUserId() throws Exception{
        given(repo.findAllByUserId(1)).willReturn(new ArrayList<Portfolio>());

        mvc.perform(get("/portfolios/users/all/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
    }

    @Test
    void testPost() throws Exception{
        Portfolio port = new Portfolio(1, "new portfilio", new User(1, "test", "user" , "test@email.com" , "password", false), false, false, false, "");
        
        given(repo.save(port)).willReturn(port);

        mvc.perform(post("/portfolios")
            .contentType("application/json;charset=utf-8")
            .content(new ObjectMapper().writeValueAsString(port)))

            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andReturn();
    }

    void testUpdate() throws Exception{
        Portfolio port = new Portfolio(1, "new portfilio", new User(1, "test", "user" , "test@email.com" , "password", false), false, false, false, "");
        Portfolio port2 = new Portfolio(1, "new portfilio name", new User(1, "test", "user" , "test@email.com" , "password", false), true, true, true, "feedback");
        Optional<Portfolio> returned = Optional.of(port);
        

        given(repo.findById(1)).willReturn(returned);

        mvc.perform(post("/portfolios/1")
            .contentType("application/json")
            .content(new ObjectMapper().writeValueAsString(port2)))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(status().isOk());
    }

    @Test
    void testdelete() throws Exception {
        Portfolio port = new Portfolio(1, "new portfilio", new User(1, "test", "user" , "test@email.com" , "password", false), false, false, false, "");
        Optional<Portfolio> returned = Optional.of(port);

        given(repo.findById(1)).willReturn(returned);

        mvc.perform(delete("/portfolios/1"))
            .andExpect(status().isOk());
    }

    
}

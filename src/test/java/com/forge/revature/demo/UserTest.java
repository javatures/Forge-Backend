package com.forge.revature.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forge.revature.controllers.UserController;
import com.forge.revature.models.User;
import com.forge.revature.repo.UserRepo;

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
public class UserTest {
    

    private MockMvc mvc;

    @MockBean
    UserRepo repo;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .standaloneSetup(new UserController(repo))
            .build();
    }

    @Test
    void testGetall() throws Exception {
        System.out.println(repo);
        given(repo.findAll())
            .willReturn(new ArrayList<User>());

        mvc.perform(get("/users"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testGetById() throws Exception{
        given(repo.findById(1)).willReturn(Optional.of(new User()));


        mvc.perform(get("/users/1"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andReturn();
    }

    @Test
    void testPost() throws Exception{
        User user = new User(1, "test user", "password", false);
            
        
        given(repo.save(user)).willReturn(user);

        mvc.perform(post("/users")
            .contentType("application/json;charset=utf-8")
            .content(new ObjectMapper().writeValueAsString(user)))

            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andReturn();
    }



}

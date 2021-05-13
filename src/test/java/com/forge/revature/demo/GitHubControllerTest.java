package com.forge.revature.demo;

import com.forge.revature.controllers.GitHubController;
import com.forge.revature.repo.GitHubRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class GitHubControllerTest {
  private MockMvc mockMvc;

  @MockBean
  GitHubRepo gitHubRepo;

  @BeforeEach
  public void setup(){
    this.mockMvc = MockMvcBuilders.standaloneSetup(new GitHubController()).build();
  }

  @Test
  void testIndex() throws Exception{
    this.mockMvc.perform(get("/api/v1/github/all"))
      .andExpect(status().isOk())
      .andDo(MockMvcResultHandlers.print())
      .andReturn();
  }
  
}

package com.forge.revature.demo;

import com.forge.revature.controllers.GitHubController;
import com.forge.revature.repo.GitHubRepo;
import com.forge.revature.models.GitHub;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GitHubController.class)
public class GitHubControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private GitHubRepo gitHubRepo;

  private GitHub gitHub;

  @BeforeEach
  public void setup() {
    this.gitHub = new GitHub("www.myUrl.com", "Image");
    this.gitHub.setId(1);
  }

  @Test
  public void givenGitHubGetAllReturnJsonArray() throws Exception {
    List<GitHub> allGitHub = Arrays.asList(gitHub);
  
    given(gitHubRepo.findAll()).willReturn(allGitHub);

    mvc.perform(get("/github")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].url", is(gitHub.getUrl())));
  }

  @Test
  public void givenGitHubGetGitHubReturnJson() throws Exception {
    given(gitHubRepo.findById(Mockito.anyInt())).willReturn(Optional.of(gitHub));

    mvc.perform(get("/github/1")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.url", is(gitHub.getUrl()))); //making sure getting the right data
  }

  @Test
  public void postGitHubCreatesGitHub() throws Exception {
    given(gitHubRepo.save(Mockito.any())).willReturn(gitHub);

    mvc.perform(post("/github")
      .contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(gitHub)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.url", is(gitHub.getUrl())));
      
    verify(gitHubRepo, VerificationModeFactory.times(1)).save(Mockito.any());
    reset(gitHubRepo);
  }
}

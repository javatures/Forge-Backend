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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    this.gitHub = new GitHub("www.github.com/user", "profile pic");
    this.gitHub.setId(1);
  }

  @Test
  public void testGetAll() throws Exception {
    List<GitHub> allGitHub = Arrays.asList(gitHub);
  
    given(gitHubRepo.findAll()).willReturn(allGitHub);

    mvc.perform(get("/github")
      .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].url", is(gitHub.getUrl())));
  }

  @Test
  public void testGet() throws Exception {
    given(gitHubRepo.findById(1)).willReturn(Optional.of(gitHub));
    given(gitHubRepo.findById(2)).willReturn(Optional.empty());

    mvc.perform(get("/github/1")
      .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.url", is(gitHub.getUrl()))); //making sure getting the right data

    //checking when id does not exist (findById returns empty optional)
    mvc.perform(delete("/github/2"))
      .andDo(print())
      .andExpect(status().isNotFound())
      .andExpect(content().string(containsString("GitHub not Found")));
  }

  @Test
  public void testPost() throws Exception {
    given(gitHubRepo.save(Mockito.any())).willReturn(gitHub);

    mvc.perform(post("/github")
      .contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(gitHub)))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.url", is(gitHub.getUrl())));
  }

  @Test
  void testDelete() throws Exception {
    given(gitHubRepo.findById(1)).willReturn(Optional.of(gitHub));
    given(gitHubRepo.findById(2)).willReturn(Optional.empty());

    mvc.perform(delete("/github/1"))
      .andDo(print())
      .andExpect(status().isOk());

    //checking when id does not exist (findById returns empty optional)
    mvc.perform(delete("/github/2"))
      .andDo(print())
      .andExpect(status().isNotFound())
      .andExpect(content().string(containsString("GitHub not Found")));
  }

  @Test
  void testUpdate() throws Exception {
    given(gitHubRepo.findById(2)).willReturn(Optional.empty());

    GitHub newGit = new GitHub("www.github.com/updatedUser", "updated profile pic");
    newGit.setId(2);

    //checking when id does not exist (findById returns empty optional)
    mvc.perform(put("/github")
      .contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(newGit)))
      .andDo(print())
      .andExpect(status().isNotFound())
      .andExpect(content().string(containsString("GitHub not Found")));
    
    newGit.setId(1);

    given(gitHubRepo.save(Mockito.any())).willReturn(newGit);

    mvc.perform(put("/github")
      .contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(newGit)))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.url", is(newGit.getUrl())));
  }
}

package com.forge.revature.demo;

import com.forge.revature.controllers.HonorsController;
import com.forge.revature.repo.HonorsRepo;
import com.forge.revature.models.Honors;

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
@WebMvcTest(HonorsController.class)
public class HonorsControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private HonorsRepo honorsRepo;

  private Honors honor;

  @BeforeEach
  public void setup() {
    this.honor = new Honors("Developer of the Year", "Top Performing Developer", "2019", "Revature");
    this.honor.setId(1);
  }

  @Test
  public void givenHonorsGetAllReturnJsonArray() throws Exception {
    List<Honors> allHonors = Arrays.asList(honor);
  
    given(honorsRepo.findAll()).willReturn(allHonors);

    mvc.perform(get("/honors")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].title", is(honor.getTitle())));
  }

  @Test
  public void givenHonorsGetHonorsReturnJson() throws Exception {
    given(honorsRepo.findById(Mockito.anyInt())).willReturn(Optional.of(honor));

    mvc.perform(get("/honors/1")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", is(honor.getTitle()))); //making sure getting the right data
  }

  @Test
  public void postHonorsCreatesHonors() throws Exception {
    given(honorsRepo.save(Mockito.any())).willReturn(honor);

    mvc.perform(post("/honors")
      .contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(honor)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", is(honor.getTitle())));
      
    verify(honorsRepo, VerificationModeFactory.times(1)).save(Mockito.any());
    reset(honorsRepo);
  }
}

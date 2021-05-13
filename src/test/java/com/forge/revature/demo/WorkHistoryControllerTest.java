package com.forge.revature.demo;

import com.forge.revature.controllers.WorkHistoryController;
import com.forge.revature.repo.WorkHistoryRepo;
import com.forge.revature.models.WorkHistory;

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
@WebMvcTest(WorkHistoryController.class)
public class WorkHistoryControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private WorkHistoryRepo workHistoryRepo;

  private WorkHistory workHistory;

  @BeforeEach
  public void setup() {
    this.workHistory = new WorkHistory("Scrum Master", "Leading team meetings", "In charge of all scrum meetings", "Java", "May 20, 2010 - March 13, 2021");
    this.workHistory.setId(1);
  }

  @Test
  public void givenWorkHistoryGetAllReturnJsonArray() throws Exception {
    List<WorkHistory> allWorkHistory = Arrays.asList(workHistory);
  
    given(workHistoryRepo.findAll()).willReturn(allWorkHistory);

    mvc.perform(get("/workhistory")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].title", is(workHistory.getTitle())));
  }

  @Test
  public void givenWorkHistoryGetWorkHistoryReturnJson() throws Exception {
    given(workHistoryRepo.findById(Mockito.anyInt())).willReturn(Optional.of(workHistory));

    mvc.perform(get("/workhistory/1")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", is(workHistory.getTitle()))); //making sure getting the right data
  }

  @Test
  public void postWorkHistoryCreatesWorkHistory() throws Exception {
    given(workHistoryRepo.save(Mockito.any())).willReturn(workHistory);

    mvc.perform(post("/workhistory")
      .contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(workHistory)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", is(workHistory.getTitle())));
      
    verify(workHistoryRepo, VerificationModeFactory.times(1)).save(Mockito.any());
    reset(workHistoryRepo);
  }
}

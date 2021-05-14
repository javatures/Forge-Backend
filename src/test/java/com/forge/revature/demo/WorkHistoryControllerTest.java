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
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.BDDMockito.given;

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
  public void testGetAll() throws Exception {
    List<WorkHistory> allWorkHistory = Arrays.asList(workHistory);
  
    given(workHistoryRepo.findAll()).willReturn(allWorkHistory);

    mvc.perform(get("/workhistory")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", hasSize(1)))
      .andExpect(jsonPath("$[0].title", is(workHistory.getTitle())));
  }

  @Test
  public void testGet() throws Exception {
    given(workHistoryRepo.findById(1)).willReturn(Optional.of(workHistory));
    given(workHistoryRepo.findById(2)).willReturn(Optional.empty());

    mvc.perform(get("/workhistory/1")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", is(workHistory.getTitle()))); //making sure getting the right data

    //checking when id does not exist (findById returns empty optional)
    mvc.perform(get("/workhistory/2"))
      .andDo(print())
      .andExpect(status().isNotFound())
      .andExpect(content().string(containsString("WorkHistory not Found")));
  }

  @Test
  public void testPost() throws Exception {
    given(workHistoryRepo.save(Mockito.any())).willReturn(workHistory);

    mvc.perform(post("/workhistory")
      .contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(workHistory)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", is(workHistory.getTitle())));
  }

  @Test
  void testDelete() throws Exception {
    given(workHistoryRepo.findById(1)).willReturn(Optional.of(workHistory));
    given(workHistoryRepo.findById(2)).willReturn(Optional.empty());

    mvc.perform(delete("/workhistory/1"))
      .andDo(print())
      .andExpect(status().isOk());

    //checking when id does not exist (findById returns empty optional)
    mvc.perform(delete("/workhistory/2"))
      .andDo(print())
      .andExpect(status().isNotFound())
      .andExpect(content().string(containsString("WorkHistory not Found")));
  }

  @Test
  void testUpdate() throws Exception {
    given(workHistoryRepo.findById(1)).willReturn(Optional.of(workHistory));
    given(workHistoryRepo.findById(2)).willReturn(Optional.empty());

    WorkHistory newGit = new WorkHistory("Scrum Master", "Leading team meetings", "In charge of all scrum meetings", "Java", "May 20, 2010 - March 13, 2021");
    newGit.setId(2);

    //checking when id does not exist (findById returns empty optional)
    mvc.perform(put("/workhistory")
      .contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(newGit)))
      .andDo(print())
      .andExpect(status().isNotFound())
      .andExpect(content().string(containsString("WorkHistory not Found")));
    
    newGit.setId(1);

    given(workHistoryRepo.save(Mockito.any())).willReturn(newGit);

    mvc.perform(put("/workhistory")
      .contentType(MediaType.APPLICATION_JSON)
      .content(new ObjectMapper().writeValueAsString(newGit)))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", is(newGit.getTitle())));
  }
}

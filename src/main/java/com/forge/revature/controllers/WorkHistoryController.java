package com.forge.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.forge.revature.models.WorkHistory;
import com.forge.revature.repo.WorkHistoryRepo;
import com.forge.revature.exception.NotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("workhistory")
public class WorkHistoryController {
  @Autowired
  private WorkHistoryRepo workHistoryRepo;

  @GetMapping
  public List<WorkHistory> getAll() {
    List<WorkHistory> workHistory = workHistoryRepo.findAll();
    return workHistory;
  }

  @GetMapping("/{id}")
  public WorkHistory getWorkHistory(@PathVariable int id) {
    return workHistoryRepo.findById(id)
      .orElseThrow(() -> new NotFoundException("WorkHistory not Found for ID: " + id));
  }

  @PostMapping
  public WorkHistory postWorkHistory(@RequestBody WorkHistory workHistory) {
    return workHistoryRepo.save(workHistory);
  }

  @PutMapping
  public WorkHistory updateWorkHistory(@RequestBody WorkHistory updateWorkHist) {
    WorkHistory prevWorkHist = workHistoryRepo.findById(updateWorkHist.getId()).orElseThrow(() -> new NotFoundException("WorkHistory not Found for ID: " + updateWorkHist.getId()));

    prevWorkHist.setTitle(updateWorkHist.getTitle());
    prevWorkHist.setResponsibilities(updateWorkHist.getResponsibilities());
    prevWorkHist.setDescription(updateWorkHist.getDescription());
    prevWorkHist.setTools(updateWorkHist.getTools());
    prevWorkHist.setDate(updateWorkHist.getDate());

    return workHistoryRepo.save(prevWorkHist);
  }

  @DeleteMapping("/{id}")
  public void deleteWorkHistory(@PathVariable int id) {
    WorkHistory exist = workHistoryRepo.findById(id).orElseThrow(() -> new NotFoundException("WorkHistory not Found for ID: " + id));
    workHistoryRepo.deleteById(exist.getId());
  }
}
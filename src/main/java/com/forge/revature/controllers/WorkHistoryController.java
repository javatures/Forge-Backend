package com.forge.revature.controllers;

import java.util.List;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forge.revature.models.WorkHistory;
import com.forge.revature.repo.WorkHistoryRepo;

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
        return workHistoryRepo.findById(id).get();
    }

    @PostMapping
    public WorkHistory postWorkHistory(@RequestBody WorkHistory workHistory) {
        return workHistoryRepo.save(workHistory);
    }
}
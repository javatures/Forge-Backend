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

import com.forge.revature.models.WorkExperience;
import com.forge.revature.repo.WorkExperienceRepo;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("WorkExperience")
public class WorkExperienceController {
    @Autowired
    WorkExperienceRepo workExperienceRepo;

    @GetMapping
    public List<WorkExperience> getAll() {
        List<WorkExperience> workExperience = StreamSupport.stream(workExperienceRepo.findAll().spliterator(), false)
            .collect(Collectors.toList());
        return workExperience;
    }

    @GetMapping("/{id}")
    public WorkExperience getWorkExperience(@PathVariable(name = "id") int id) {
        return workExperienceRepo.findById(id).get();
    }

    @PostMapping
    public WorkExperience postWorkExperience(@RequestBody WorkExperience workExperience) {
        return workExperienceRepo.save(workExperience);
    }
}
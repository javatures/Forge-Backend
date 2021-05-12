package com.forge.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.forge.revature.models.WorkExperience;
import com.forge.revature.repo.WorkExperienceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class WorkExperienceController {
    @Autowired
    WorkExperienceRepo repo;

    public WorkExperienceController() {
    }

    public WorkExperienceController(WorkExperienceRepo repo) {
        this.repo = repo;
    }

    @RequestMapping(value = "/workexperience", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<WorkExperience>> allExperience() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/workexperience/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Optional<WorkExperience>> getExperience(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(repo.findById(id), HttpStatus.OK);
    }
}

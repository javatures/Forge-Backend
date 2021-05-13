package com.forge.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.forge.revature.models.Project;
import com.forge.revature.repo.ProjectRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ProjectController {
    @Autowired
    ProjectRepo repo;

    public ProjectController() {
    }

    public ProjectController(ProjectRepo repo) {
        this.repo = repo;
    }

    @RequestMapping(value = "/projects", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> allExperience() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/projects/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Optional<Project>> getExperience(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(repo.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/projects", consumes = "application/json", method = RequestMethod.POST)
    public void createExperience(@RequestBody Project proj) {
        repo.save(proj);
    }

    @RequestMapping(value = "/projects/{id}", consumes = "application/json", method = RequestMethod.POST)
    public void updateExperience(@PathVariable(name = "id") long id, @RequestBody Project proj) {
        Optional<Project> update = repo.findById(id);

        if (update.isPresent()) {
            update.get().setName(proj.getName());
            update.get().setDescription(proj.getDescription());
            update.get().setResponsibilities(proj.getResponsibilities());
            update.get().setHours(proj.getHours());
            update.get().setDuration(proj.getDuration());

            proj = update.get();
            repo.save(proj);
        }
    }

    @RequestMapping(value = "/projects/{id}", method = RequestMethod.DELETE)
    public void deleteExperience(@PathVariable(name = "id") long id) {
        repo.deleteById(id);
    }
}
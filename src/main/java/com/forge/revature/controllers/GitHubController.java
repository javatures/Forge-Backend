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

import com.forge.revature.models.GitHub;
import com.forge.revature.repo.GitHubRepo;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("github")
public class GitHubController {
    @Autowired
    GitHubRepo gitRepo;

    @GetMapping
    public List<GitHub> getAll() {
        List<GitHub> git = StreamSupport.stream(gitRepo.findAll().spliterator(), false)
            .collect(Collectors.toList());
        return git;
    }

    @GetMapping("/{id}")
    public GitHub getGitHub(@PathVariable(name = "id") int id) {
        return gitRepo.findById(id).get();
    }

    @PostMapping
    public GitHub postGitHub(@RequestBody GitHub gitHub) {
        return gitRepo.save(gitHub);
    }
}
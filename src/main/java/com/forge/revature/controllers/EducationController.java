package com.forge.revature.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.forge.revature.models.Education;
import com.forge.revature.repo.EducationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("education")
public class EducationController {
    @Autowired
    EducationRepo educationRepo;

    @GetMapping
    public List<Education> getAll() {
        List<Education> educations = StreamSupport.stream(educationRepo.findAll().spliterator(), false)
            .collect(Collectors.toList());
        return educations;
    }

    @GetMapping("/{id}")
    public Education getAboutMe(@PathVariable int id) {
        return educationRepo.findById(id).get();
    }

    @PostMapping
    public Education postAboutMe(@RequestBody Education education) {
        return educationRepo.save(education);
    }

    @GetMapping("/user/{id}")
    public Education getUserAboutMe(@RequestBody int userId) {
        Education retrievedEducation = null;
        //get education based on user id
        return retrievedEducation;
    }

    @GetMapping("/portfolio/{id}")
    public Education getPortfolioAboutMe(@RequestBody int portfolioId) {
        Education retrievedEducation = null;
        //get education based on portfolio id
        return retrievedEducation;
    }
}

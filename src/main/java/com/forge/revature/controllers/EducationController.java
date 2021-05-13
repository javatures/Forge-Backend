package com.forge.revature.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.forge.revature.models.Education;
import com.forge.revature.repo.EducationRepo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("education")
public class EducationController {
    @Autowired
    EducationRepo educationRepo;

    public EducationController() {}

    public EducationController(EducationRepo educationRepo) {
        this.educationRepo = educationRepo;
    }

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

    @PostMapping("/{id}")
    public void updateEducation(@RequestBody Education newEducation, @PathVariable(name = "id") int educationId) {
        Optional<Education> oldEducation = educationRepo.findById(educationId);

        if(oldEducation.isPresent())
        {
            BeanUtils.copyProperties(newEducation, oldEducation);

            educationRepo.save(oldEducation.get());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable(name = "id") int educationId) {
        educationRepo.deleteById(educationId);
    }

    //needs to be refined once access to Portfolio is gained
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

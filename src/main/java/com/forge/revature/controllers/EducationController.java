package com.forge.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.forge.revature.models.Education;
import com.forge.revature.repo.EducationRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    public EducationController() {
    }

    public EducationController(EducationRepo educationRepo) {
        this.educationRepo = educationRepo;
    }

    @GetMapping
    public List<Education> getAll() {
        List<Education> educations = educationRepo.findAll();
        return educations;
    }

    @GetMapping("/{id}")
    public Education getEducation(@PathVariable int id) {
        return educationRepo.findById(id).get();
    }

    @PostMapping
    public Education postEducation(@RequestBody Education education) {
        return educationRepo.save(education);
    }

    @PostMapping("/{id}")
    public void updateEducation(@RequestBody Education newEducation, @PathVariable(name = "id") int educationId) {
        Optional<Education> oldEducation = educationRepo.findById(educationId);

        if (oldEducation.isPresent()) {
            oldEducation.get().setUniversity(newEducation.getUniversity());
            oldEducation.get().setDegree(newEducation.getDegree());
            oldEducation.get().setGraduationDate(newEducation.getGraduationDate());
            oldEducation.get().setGpa(newEducation.getGpa());
            if (!newEducation.getLogoUrl().isEmpty() && !newEducation.getLogoUrl().equals("")) {
                oldEducation.get().setLogoUrl(newEducation.getLogoUrl());
            }

            educationRepo.save(oldEducation.get());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable(name = "id") int educationId) {
        educationRepo.deleteById(educationId);
    }

    @GetMapping("/user/{id}")
    public Education getUserEducation(@PathVariable(name = "id") int userId) {
        Optional<Education> retrievedEducation = educationRepo.findByPortfolioUserId(userId);

        if (retrievedEducation.isPresent()) {
            return retrievedEducation.get();
        }

        return null;
    }

    @GetMapping("/portfolio/{id}")
    public Education getPortfolioEducation(@PathVariable(name = "id") int portfolioId) {
        Optional<Education> retrievedEducation = educationRepo.findByPortfolioId(portfolioId);

        if (retrievedEducation.isPresent()) {
            return retrievedEducation.get();
        }

        return null;
    }

    @GetMapping("/user/all/{id}")
    public List<Education> getUserEducations(@PathVariable(name = "id") int userId) {
        List<Education> retrievedEducations = educationRepo.findAllByPortfolioUserId(userId);

        return retrievedEducations;
    }

    @GetMapping("/portfolio/all/{id}")
    public List<Education> getPortfolioEducations(@PathVariable(name = "id") int portfolioId) {
        List<Education> retrievedEducations = educationRepo.findAllByPortfolioId(portfolioId);

        return retrievedEducations;
    }
}

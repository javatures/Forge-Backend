package com.forge.revature.controllers;

import java.util.List;
import java.util.Optional;

import com.forge.revature.models.AboutMe;
import com.forge.revature.repo.AboutMeRepo;

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
@RequestMapping("aboutMe")
public class AboutMeController {
    @Autowired
    AboutMeRepo aboutMeRepo;

    public AboutMeController() {
    }

    public AboutMeController(AboutMeRepo repo) {
        this.aboutMeRepo = repo;
    }

    @GetMapping
    public List<AboutMe> getAll() {
        List<AboutMe> aboutMes = aboutMeRepo.findAll();
        return aboutMes;
    }

    @PostMapping
    public AboutMe postAboutMe(@RequestBody AboutMe aboutMe) {
        return aboutMeRepo.save(aboutMe);
    }

    @GetMapping("/{id}")
    public AboutMe getAboutMe(@PathVariable(name = "id") int id) {
        return aboutMeRepo.findById(id).get();
    }

    @PostMapping("/{id}")
    public void updateAboutMe(@RequestBody AboutMe newAboutMe, @PathVariable(name = "id") int aboutMeId) {
        Optional<AboutMe> oldAboutMe = aboutMeRepo.findById(aboutMeId);

        if (oldAboutMe.isPresent()) {

            oldAboutMe.get().setBio(newAboutMe.getBio());
            oldAboutMe.get().setEmail(newAboutMe.getEmail());
            oldAboutMe.get().setPhone(newAboutMe.getPhone());

            aboutMeRepo.save(oldAboutMe.get());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAboutMe(@PathVariable(name = "id") int aboutMeId) {
        aboutMeRepo.deleteById(aboutMeId);
    }

    @GetMapping("/user/{id}")
    public AboutMe getUserAboutMe(@PathVariable(name = "id") int userId) {
        Optional<AboutMe> retrievedAboutMe = aboutMeRepo.findByPortfolioUserId(userId);

        if (retrievedAboutMe.isPresent()) {
            return retrievedAboutMe.get();
        }
        return null;
    }

    @GetMapping("/portfolio/{id}")
    public AboutMe getPortfolioAboutMe(@PathVariable(name = "id") int portfolioId) {
        Optional<AboutMe> retrievedAboutMe = aboutMeRepo.findByPortfolioId(portfolioId);

        if (retrievedAboutMe.isPresent()) {
            return retrievedAboutMe.get();
        }
        return null;
    }
}

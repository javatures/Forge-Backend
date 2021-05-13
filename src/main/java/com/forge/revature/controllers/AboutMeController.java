package com.forge.revature.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.forge.revature.models.AboutMe;
import com.forge.revature.repo.AboutMeRepo;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aboutMe")
public class AboutMeController {
    @Autowired
    AboutMeRepo aboutMeRepo;

    public AboutMeController() {}

    public AboutMeController(AboutMeRepo repo) {
        this.aboutMeRepo = repo;
    }

    @GetMapping
    public List<AboutMe> getAll() {
        List<AboutMe> aboutMes = StreamSupport.stream(aboutMeRepo.findAll().spliterator(), false)
            .collect(Collectors.toList());
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

        if(oldAboutMe.isPresent())
        {
            BeanUtils.copyProperties(newAboutMe, oldAboutMe);
            /*
            oldAboutMe.get().setBio(newAboutMe.getBio());
            oldAboutMe.get().setEmail(newAboutMe.getEmail());
            oldAboutMe.get().setPhone(newAboutMe.getPhone());
            */

            aboutMeRepo.save(oldAboutMe.get());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAboutMe(@PathVariable(name = "id") int aboutMeId) {
        aboutMeRepo.deleteById(aboutMeId);
    }

    //needs to be refined once access to Portfolio is gained
    @GetMapping("/user/{id}") 
    public AboutMe getUserAboutMe(@PathVariable(name = "id") int userId) {
        AboutMe retrievedAboutMe = null;
        //get aboutMe based on user id
        return retrievedAboutMe;
    }

    @GetMapping("/portfolio/{id}") 
    public AboutMe getPortfolioAboutMe(@PathVariable(name = "id") int portfolioId) {
        AboutMe retrievedAboutMe = null;
        //get aboutMe based on portfolio id
        return retrievedAboutMe;
    }
}

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

import com.forge.revature.models.Honors;
import com.forge.revature.repo.HonorsRepo;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("honors")
public class HonorsController {
    @Autowired
     private HonorsRepo honorsRepo;

    @GetMapping
    public List<Honors> getAll() {
        List<Honors> honors = honorsRepo.findAll();
        return honors;
    }

    @GetMapping("/{id}")
    public Honors getHonors(@PathVariable int id) {
        return honorsRepo.findById(id).get();
    }

    @PostMapping
    public Honors postHonors(@RequestBody Honors honors) {
        return honorsRepo.save(honors);
    }
}
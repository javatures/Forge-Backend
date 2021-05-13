package com.forge.revature.controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;
import java.util.Optional;

import com.forge.revature.models.User;
import com.forge.revature.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserController() {
    }

    @GetMapping
    public List<User> getAll() {
        List<User> users = StreamSupport.stream(userRepo.findAll().spliterator(), false)
            .collect(Collectors.toList());
        return users;
    }

    @GetMapping("/{id}")
    public User getByID(@PathVariable(name = "id") int id){
        return userRepo.findById(id).get();
    }

    @PostMapping
    public User postUser(@RequestBody User user){
        return userRepo.save(user);
    }

    @PostMapping("user/{id}")
    public void updateUser(@PathVariable int id , @RequestBody User newU){
        Optional<User> old = userRepo.findById(id);

        if(old.isPresent()){
            old.get().setAdmin(newU.isAdmin());
            old.get().setName(newU.getName());
            old.get().setPassword(newU.getPassword());
            userRepo.save(old.get());
        }


    }
    
}

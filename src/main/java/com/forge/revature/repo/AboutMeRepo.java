package com.forge.revature.repo;

import com.forge.revature.models.AboutMe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutMeRepo extends JpaRepository<AboutMe, Integer>{
    
}

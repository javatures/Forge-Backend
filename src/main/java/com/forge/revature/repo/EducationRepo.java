package com.forge.revature.repo;

import com.forge.revature.models.Education;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepo extends JpaRepository<Education, Integer>{
    
}
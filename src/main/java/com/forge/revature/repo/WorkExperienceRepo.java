package com.forge.revature.repo;

import com.forge.revature.models.WorkExperience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkExperienceRepo extends JpaRepository<WorkExperience, Long>{
    
}

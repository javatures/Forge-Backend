package com.forge.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forge.revature.models.WorkExperience;

@Repository
public interface WorkExperienceRepo extends JpaRepository<WorkExperience, Integer>{

}
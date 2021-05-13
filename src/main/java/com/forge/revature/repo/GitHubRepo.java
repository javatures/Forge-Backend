package com.forge.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forge.revature.models.GitHub;

@Repository
public interface GitHubRepo extends JpaRepository<GitHub, Integer>{

}
package com.forge.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.forge.revature.models.GitHub;
import com.forge.revature.models.Portfolio;

@Repository
public interface GitHubRepo extends JpaRepository<GitHub, Integer>{

  Optional<GitHub> findByPortfolio(Portfolio portfolio);

}
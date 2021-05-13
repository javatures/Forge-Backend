package com.forge.revature.repo;

import java.util.Optional;

import com.forge.revature.models.Education;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepo extends JpaRepository<Education, Integer>{
    Optional<Education> findByPortfolioId(Integer portfolioId);
    Optional<Education> findByPortfolioUserId(Integer userId);
}
package com.forge.revature.repo;

import java.util.Optional;

import com.forge.revature.models.AboutMe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutMeRepo extends JpaRepository<AboutMe, Integer>{
    Optional<AboutMe> findByPortfolioId(Integer portfolioId);
    Optional<AboutMe> findByPortfolioUserId(Integer userId);
}

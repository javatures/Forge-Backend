package com.forge.revature.repo;

import java.util.Optional;

import com.forge.revature.models.AboutMe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutMeRepo extends JpaRepository<AboutMe, Integer>{
    //needs to be refined once access to Portfolio is gained
    //Optional<AboutMe> findByPortfolioId(Integer portfolioId);
}

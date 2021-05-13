package com.forge.revature.repo;

import com.forge.revature.models.Equivalency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquivalencyRepo extends JpaRepository<Equivalency, Integer>{
    
}

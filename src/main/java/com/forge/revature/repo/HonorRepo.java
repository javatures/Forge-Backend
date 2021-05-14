package com.forge.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forge.revature.models.Honor;

@Repository
public interface HonorRepo extends JpaRepository<Honor, Integer>{

}
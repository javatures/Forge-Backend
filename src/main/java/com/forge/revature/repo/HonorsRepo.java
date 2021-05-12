package com.forge.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forge.revature.models.Honors;

@Repository
public interface HonorsRepo extends JpaRepository<Honors, Integer>{

}
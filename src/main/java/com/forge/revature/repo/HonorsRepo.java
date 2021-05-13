package com.forge.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forge.revature.models.Honors;

@Repository
public interface HonorsRepo extends JpaRepository<Honors, Integer>{

}
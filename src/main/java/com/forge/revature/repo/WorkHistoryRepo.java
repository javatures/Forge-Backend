package com.forge.revature.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.forge.revature.models.WorkHistory;

@Repository
public interface WorkHistoryRepo extends JpaRepository<WorkHistory, Integer>{

}
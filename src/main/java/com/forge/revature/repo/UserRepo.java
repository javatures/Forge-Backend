package com.forge.revature.repo;

import java.util.Optional;

import com.forge.revature.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
    public Optional<User> findByEmail(String email);

}

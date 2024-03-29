package com.example.demo.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByName(String aName);
    Optional<User> findUserByGpa(String aGpa);
}

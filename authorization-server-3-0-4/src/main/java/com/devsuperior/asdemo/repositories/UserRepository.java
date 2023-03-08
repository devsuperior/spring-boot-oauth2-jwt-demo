package com.devsuperior.asdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.asdemo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
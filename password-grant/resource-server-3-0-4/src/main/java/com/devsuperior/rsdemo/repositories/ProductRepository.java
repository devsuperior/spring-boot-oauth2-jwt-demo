package com.devsuperior.rsdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.rsdemo.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
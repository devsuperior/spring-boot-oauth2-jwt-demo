package com.devsuperior.rsdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.rsdemo.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

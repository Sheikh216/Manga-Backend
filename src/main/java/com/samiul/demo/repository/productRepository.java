package com.samiul.demo.repository;
import com.samiul.demo.model.products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<products,Long> {
}
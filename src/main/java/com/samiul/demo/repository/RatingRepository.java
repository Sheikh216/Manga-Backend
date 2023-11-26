package com.samiul.demo.repository;

import com.samiul.demo.model.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingRepository extends JpaRepository<ProductRating, Long> {

    @Query("SELECT p FROM ProductRating p WHERE p.user.email = :email")
    List<ProductRating> findByUser(String email);
}

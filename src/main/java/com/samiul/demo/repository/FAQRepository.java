package com.samiul.demo.repository;

import com.samiul.demo.model.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
    List<FAQ> findByIsRepliedTrue();
    List<FAQ> findByIsRepliedFalse();
}

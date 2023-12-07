package com.samiul.demo.repository;

import com.samiul.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByIsRepliedTrue();
    List<Message> findByIsRepliedFalse();
}

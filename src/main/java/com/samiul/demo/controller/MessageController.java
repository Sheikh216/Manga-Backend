package com.samiul.demo.controller;

import com.samiul.demo.model.Message;
import com.samiul.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin("http://localhost:3000")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/unreplied")
    @Transactional
    public ResponseEntity<List<Message>> getAllUnRepliedMessage() {
        return ResponseEntity.ok(this.messageRepository.findByIsRepliedFalse());
    }


    @GetMapping("/replied")
    @Transactional
    public ResponseEntity<List<Message>> getAllRepliedMessage() {
        return ResponseEntity.ok(this.messageRepository.findByIsRepliedTrue());
    }

    @PostMapping
    public ResponseEntity<Message> getAllRepliedMessage(@RequestBody Message message) {
        return ResponseEntity.ok(this.messageRepository.save(message));
    }

    @PostMapping("{id}")
    public ResponseEntity<Void> replyToUser(@PathVariable Long id) {
        var existingEntity = this.messageRepository.findById(id).orElseThrow();
        existingEntity.setReplied(true);
        this.messageRepository.save(existingEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.messageRepository.findById(id)
                .orElseThrow());
    }
}

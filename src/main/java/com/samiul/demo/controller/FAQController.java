package com.samiul.demo.controller;

import com.samiul.demo.domain.FAQReply;
import com.samiul.demo.model.FAQ;
import com.samiul.demo.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faq")
@CrossOrigin("http://localhost:3000")
public class FAQController {
    @Autowired
    private FAQRepository faqRepository;

    @GetMapping("/unreplied")
    @Transactional
    public ResponseEntity<List<FAQ>> getAllUnRepliedFAQ() {
        return ResponseEntity.ok(this.faqRepository.findByIsRepliedFalse());
    }


    @GetMapping("/replied")
    @Transactional
    public ResponseEntity<List<FAQ>> getAllRepliedFAQ() {
        return ResponseEntity.ok(this.faqRepository.findByIsRepliedTrue());
    }

    @PostMapping
    public ResponseEntity<FAQ> getAllRepliedFAQ(@RequestBody FAQ faq) {
        return ResponseEntity.ok(this.faqRepository.save(faq));
    }

    @PostMapping("{id}")
    public ResponseEntity<Void> replyToUser(@PathVariable Long id) {
        var existingEntity = this.faqRepository.findById(id).orElseThrow();
        existingEntity.setReplied(true);
        this.faqRepository.save(existingEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<FAQ> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.faqRepository.findById(id)
                .orElseThrow());
    }
}

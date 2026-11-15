package com.main.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.main.dto.QuestionRequest;
import com.main.dto.QuestionResponse;
import com.main.service.QuestionService;

import jakarta.validation.Valid;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // User APIs

    @GetMapping("/api/questions")
    public ResponseEntity<List<QuestionResponse>> getPublishedQuestions() {
        return ResponseEntity.ok(
            questionService.getPublishedQuestions()
        );
    }

    @GetMapping("/api/questions/{id}")
    public ResponseEntity<QuestionResponse> getQuestion(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(
            questionService.getPublishedQuestion(id)
        );
    }

    // Admin APIs

    @PostMapping("/api/admin/question/createquestion")
    public ResponseEntity<QuestionResponse> createQuestion(
        @Valid @RequestBody QuestionRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(questionService.createQuestion(request));
    }

    @GetMapping("/api/admin/questions")
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        return ResponseEntity.ok(
            questionService.getAllQuestionsForAdmin()
        );
    }

    @PatchMapping("/api/admin/questions/{id}/publish")
    public ResponseEntity<QuestionResponse> publishQuestion(
        @PathVariable Long id
    ) {
        return ResponseEntity.ok(
            questionService.publishQuestion(id)
        );
    }
    
 // Delete question
    @DeleteMapping("/api/admin/question/deletequestion/{id}")
    public ResponseEntity<Void> deleteQuestion(
            @PathVariable Long id) {

        questionService.deleteQuestion(id);

        return ResponseEntity.noContent().build();
    }
}
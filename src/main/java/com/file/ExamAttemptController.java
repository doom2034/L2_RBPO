package com.file;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exam-attempts")
public class ExamAttemptController {
    private final ExamAttemptService examAttemptService;

    public ExamAttemptController(ExamAttemptService examAttemptService) { this.examAttemptService = examAttemptService; }

    @GetMapping
    public ResponseEntity<List<ExamAttempt>> getAll() { return ResponseEntity.ok(examAttemptService.getAllAttempts()); }

    @GetMapping("/{id}")
    public ResponseEntity<ExamAttempt> getById(@PathVariable Long id) {
        return examAttemptService.getAttemptById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Создание попытки требует только id экзамена и id студента
    @PostMapping
    public ResponseEntity<ExamAttempt> create(@RequestParam Long examId, @RequestParam Long userId) {
        try {
            ExamAttempt attempt = examAttemptService.startAttempt(examId, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(attempt);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExamAttempt> update(@PathVariable Long id, @RequestBody ExamAttempt attempt) {
        return examAttemptService.updateAttempt(id, attempt)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return examAttemptService.deleteAttempt(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

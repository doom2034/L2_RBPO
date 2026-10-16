package com.file;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exam-tasks")
public class ExamTaskController {
    private final ExamTaskService examTaskService;

    public ExamTaskController(ExamTaskService examTaskService) { this.examTaskService = examTaskService; }

    @GetMapping
    public ResponseEntity<List<ExamTask>> getAll() { return ResponseEntity.ok(examTaskService.getAllTasks()); }

    @GetMapping("/{id}")
    public ResponseEntity<ExamTask> getById(@PathVariable Long id) {
        return examTaskService.getTaskById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExamTask> create(@RequestBody ExamTask task, @RequestParam Long examId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(examTaskService.createTask(task, examId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExamTask> update(@PathVariable Long id, @RequestBody ExamTask task, @RequestParam(required = false) Long examId) {
        return examTaskService.updateTask(id, task, examId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return examTaskService.deleteTask(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

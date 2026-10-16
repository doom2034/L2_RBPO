package com.file;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/task-solutions")
public class TaskSolutionController {
    private final TaskSolutionService solutionService;

    public TaskSolutionController(TaskSolutionService solutionService) { this.solutionService = solutionService; }

    @GetMapping
    public ResponseEntity<List<TaskSolution>> getAll() { return ResponseEntity.ok(solutionService.getAllSolutions()); }

    @GetMapping("/{id}")
    public ResponseEntity<TaskSolution> getById(@PathVariable Long id) {
        return solutionService.getSolutionById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskSolution> create(@RequestBody TaskSolution solution, @RequestParam Long attemptId, @RequestParam Long taskId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(solutionService.createSolution(solution, attemptId, taskId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskSolution> update(@PathVariable Long id, @RequestBody TaskSolution solution) {
        return solutionService.updateSolution(id, solution).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return solutionService.deleteSolution(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

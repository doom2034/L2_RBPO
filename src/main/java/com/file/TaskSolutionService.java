package com.file;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TaskSolutionService {
    private final TaskSolutionRepository solutionRepository;
    private final ExamAttemptRepository attemptRepository;
    private final ExamTaskRepository taskRepository;

    public TaskSolutionService(TaskSolutionRepository solutionRepository, ExamAttemptRepository attemptRepository, ExamTaskRepository taskRepository) {
        this.solutionRepository = solutionRepository;
        this.attemptRepository = attemptRepository;
        this.taskRepository = taskRepository;
    }

    public List<TaskSolution> getAllSolutions() { return solutionRepository.findAll(); }
    public Optional<TaskSolution> getSolutionById(Long id) { return solutionRepository.findById(id); }

    public TaskSolution createSolution(TaskSolution solution, Long attemptId, Long taskId) {
        ExamAttempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new IllegalArgumentException("Attempt not found"));
        ExamTask task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        solution.setAttempt(attempt);
        solution.setTask(task);
        if (solution.getScore() == null) solution.setScore(0);
        return solutionRepository.save(solution);
    }

    public Optional<TaskSolution> updateSolution(Long id, TaskSolution updated) {
        return solutionRepository.findById(id).map(existing -> {
            if (updated.getStudentAnswer() != null) existing.setStudentAnswer(updated.getStudentAnswer());
            if (updated.getScore() != null) existing.setScore(updated.getScore());
            return solutionRepository.save(existing);
        });
    }

    public boolean deleteSolution(Long id) { return solutionRepository.deleteById(id); }
}

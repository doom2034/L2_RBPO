package com.file;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TaskSolutionRepository {
    private final List<TaskSolution> solutions = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<TaskSolution> findAll() { return new ArrayList<>(solutions); }
    public Optional<TaskSolution> findById(Long id) { return solutions.stream().filter(s -> s.getId().equals(id)).findFirst(); }
    public TaskSolution save(TaskSolution solution) {
        if (solution.getId() == null) {
            solution.setId(idGenerator.getAndIncrement());
            solutions.add(solution);
        } else {
            findById(solution.getId()).ifPresent(existing -> {
                existing.setAttempt(solution.getAttempt());
                existing.setTask(solution.getTask());
                existing.setStudentAnswer(solution.getStudentAnswer());
                existing.setScore(solution.getScore());
            });
        }
        return solution;
    }
    public boolean deleteById(Long id) { return solutions.removeIf(s -> s.getId().equals(id)); }
}

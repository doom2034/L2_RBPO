package com.file;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ExamTaskRepository {
    private final List<ExamTask> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<ExamTask> findAll() { return new ArrayList<>(tasks); }
    public Optional<ExamTask> findById(Long id) { return tasks.stream().filter(t -> t.getId().equals(id)).findFirst(); }
    public ExamTask save(ExamTask task) {
        if (task.getId() == null) {
            task.setId(idGenerator.getAndIncrement());
            tasks.add(task);
        } else {
            findById(task.getId()).ifPresent(existing -> {
                existing.setExam(task.getExam());
                existing.setDescription(task.getDescription());
                existing.setReferenceAnswer(task.getReferenceAnswer());
                existing.setMaxScore(task.getMaxScore());
            });
        }
        return task;
    }
    public boolean deleteById(Long id) { return tasks.removeIf(t -> t.getId().equals(id)); }
}

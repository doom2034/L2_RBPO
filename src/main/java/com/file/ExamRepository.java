package com.file;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ExamRepository {
    private final List<Exam> exams = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Exam> findAll() { return new ArrayList<>(exams); }
    public Optional<Exam> findById(Long id) { return exams.stream().filter(e -> e.getId().equals(id)).findFirst(); }
    public Exam save(Exam exam) {
        if (exam.getId() == null) {
            exam.setId(idGenerator.getAndIncrement());
            exams.add(exam);
        } else {
            findById(exam.getId()).ifPresent(existing -> {
                existing.setTitle(exam.getTitle());
                existing.setPassingScore(exam.getPassingScore());
                existing.setTimeLimit(exam.getTimeLimit());
                existing.setStatus(exam.getStatus());
            });
        }
        return exam;
    }
    public boolean deleteById(Long id) { return exams.removeIf(e -> e.getId().equals(id)); }
}

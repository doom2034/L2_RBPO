package com.file;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ExamAttemptRepository {
    private final List<ExamAttempt> attempts = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<ExamAttempt> findAll() { return new ArrayList<>(attempts); }
    public Optional<ExamAttempt> findById(Long id) { return attempts.stream().filter(a -> a.getId().equals(id)).findFirst(); }
    public ExamAttempt save(ExamAttempt attempt) {
        if (attempt.getId() == null) {
            attempt.setId(idGenerator.getAndIncrement());
            attempts.add(attempt);
        } else {
            findById(attempt.getId()).ifPresent(existing -> {
                existing.setExam(attempt.getExam());
                existing.setStudent(attempt.getStudent());
                existing.setStartTime(attempt.getStartTime());
                existing.setEndTime(attempt.getEndTime());
                existing.setFinalScore(attempt.getFinalScore());
                existing.setStatus(attempt.getStatus());
            });
        }
        return attempt;
    }
    public boolean deleteById(Long id) { return attempts.removeIf(a -> a.getId().equals(id)); }
}

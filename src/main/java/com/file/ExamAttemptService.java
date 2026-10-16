package com.file;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ExamAttemptService {
    private final ExamAttemptRepository examAttemptRepository;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;

    public ExamAttemptService(ExamAttemptRepository examAttemptRepository, ExamRepository examRepository, UserRepository userRepository) {
        this.examAttemptRepository = examAttemptRepository;
        this.examRepository = examRepository;
        this.userRepository = userRepository;
    }

    public List<ExamAttempt> getAllAttempts() { return examAttemptRepository.findAll(); }
    public Optional<ExamAttempt> getAttemptById(Long id) { return examAttemptRepository.findById(id); }

    public ExamAttempt startAttempt(Long examId, Long userId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ExamAttempt attempt = new ExamAttempt();
        attempt.setExam(exam);
        attempt.setStudent(user);
        attempt.setStartTime(LocalDateTime.now()); // Текущее время задаётся строго сервером
        attempt.setStatus("IN_PROGRESS");
        attempt.setFinalScore(0);
        return examAttemptRepository.save(attempt);
    }

    public Optional<ExamAttempt> updateAttempt(Long id, ExamAttempt updated) {
        return examAttemptRepository.findById(id).map(existing -> {
            if ("SUBMITTED".equals(updated.getStatus()) && !"SUBMITTED".equals(existing.getStatus())) {
                existing.setEndTime(LocalDateTime.now()); // Время окончания выставляет сервер при завершении
                existing.setStatus("SUBMITTED");
            }
            if (updated.getFinalScore() != null) existing.setFinalScore(updated.getFinalScore());
            return examAttemptRepository.save(existing);
        });
    }

    public boolean deleteAttempt(Long id) { return examAttemptRepository.deleteById(id); }
}

package com.file;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExamTaskService {
    private final ExamTaskRepository examTaskRepository;
    private final ExamRepository examRepository;

    public ExamTaskService(ExamTaskRepository examTaskRepository, ExamRepository examRepository) {
        this.examTaskRepository = examTaskRepository;
        this.examRepository = examRepository;
    }

    public List<ExamTask> getAllTasks() { return examTaskRepository.findAll(); }
    public Optional<ExamTask> getTaskById(Long id) { return examTaskRepository.findById(id); }

    public ExamTask createTask(ExamTask task, Long examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found with id: " + examId));
        task.setExam(exam);
        return examTaskRepository.save(task);
    }

    public Optional<ExamTask> updateTask(Long id, ExamTask updated, Long examId) {
        return examTaskRepository.findById(id).map(existing -> {
            if (examId != null) {
                examRepository.findById(examId).ifPresent(existing::setExam);
            }
            if (updated.getDescription() != null) existing.setDescription(updated.getDescription());
            if (updated.getReferenceAnswer() != null) existing.setReferenceAnswer(updated.getReferenceAnswer());
            if (updated.getMaxScore() != null) existing.setMaxScore(updated.getMaxScore());
            return examTaskRepository.save(existing);
        });
    }

    public boolean deleteTask(Long id) { return examTaskRepository.deleteById(id); }
}

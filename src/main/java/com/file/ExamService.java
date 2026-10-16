package com.file;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExamService {
    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) { this.examRepository = examRepository; }
    public List<Exam> getAllExams() { return examRepository.findAll(); }
    public Optional<Exam> getExamById(Long id) { return examRepository.findById(id); }
    public Exam createExam(Exam exam) {
        if (exam.getStatus() == null) exam.setStatus("DRAFT");
        return examRepository.save(exam);
    }
    public Optional<Exam> updateExam(Long id, Exam updated) {
        return examRepository.findById(id).map(existing -> {
            if (updated.getTitle() != null) existing.setTitle(updated.getTitle());
            if (updated.getPassingScore() != null) existing.setPassingScore(updated.getPassingScore());
            if (updated.getTimeLimit() != null) existing.setTimeLimit(updated.getTimeLimit());
            if (updated.getStatus() != null) existing.setStatus(updated.getStatus());
            return examRepository.save(existing);
        });
    }
    public boolean deleteExam(Long id) { return examRepository.deleteById(id); }
}

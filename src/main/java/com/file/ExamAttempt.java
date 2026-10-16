package com.file;

import java.time.LocalDateTime;

public class ExamAttempt {
    private Long id;
    private Exam exam;
    private User student;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer finalScore;
    private String status; // например, "IN_PROGRESS", "SUBMITTED"

    public ExamAttempt() {}

    public ExamAttempt(Long id, Exam exam, User student, LocalDateTime startTime, LocalDateTime endTime, Integer finalScore, String status) {
        this.id = id;
        this.exam = exam;
        this.student = student;
        this.startTime = startTime;
        this.endTime = endTime;
        this.finalScore = finalScore;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }
    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public Integer getFinalScore() { return finalScore; }
    public void setFinalScore(Integer finalScore) { this.finalScore = finalScore; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

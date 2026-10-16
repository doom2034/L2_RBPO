package com.file;

public class Exam {
    private Long id;
    private String title;
    private Integer passingScore;
    private Integer timeLimit;
    private String status;

    public Exam() {}
    public Exam(Long id, String title, Integer passingScore, Integer timeLimit, String status) {
        this.id = id;
        this.title = title;
        this.passingScore = passingScore;
        this.timeLimit = timeLimit;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getPassingScore() { return passingScore; }
    public void setPassingScore(Integer passingScore) { this.passingScore = passingScore; }
    public Integer getTimeLimit() { return timeLimit; }
    public void setTimeLimit(Integer timeLimit) { this.timeLimit = timeLimit; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

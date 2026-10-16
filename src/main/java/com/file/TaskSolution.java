package com.file;

public class TaskSolution {
    private Long id;
    private ExamAttempt attempt;
    private ExamTask task;
    private String studentAnswer;
    private Integer score; // начисленные баллы

    public TaskSolution() {}

    public TaskSolution(Long id, ExamAttempt attempt, ExamTask task, String studentAnswer, Integer score) {
        this.id = id;
        this.attempt = attempt;
        this.task = task;
        this.studentAnswer = studentAnswer;
        this.score = score;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ExamAttempt getAttempt() { return attempt; }
    public void setAttempt(ExamAttempt attempt) { this.attempt = attempt; }
    public ExamTask getTask() { return task; }
    public void setTask(ExamTask task) { this.task = task; }
    public String getStudentAnswer() { return studentAnswer; }
    public void setStudentAnswer(String studentAnswer) { this.studentAnswer = studentAnswer; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}

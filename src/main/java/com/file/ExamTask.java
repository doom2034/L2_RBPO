package com.file;

public class ExamTask {
    private Long id;
    private Exam exam; // Связь с экзаменом
    private String description; // Условие
    private String referenceAnswer; // Эталонный ответ
    private Integer maxScore; // Максимальный балл

    public ExamTask() {}

    public ExamTask(Long id, Exam exam, String description, String referenceAnswer, Integer maxScore) {
        this.id = id;
        this.exam = exam;
        this.description = description;
        this.referenceAnswer = referenceAnswer;
        this.maxScore = maxScore;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getReferenceAnswer() { return referenceAnswer; }
    public void setReferenceAnswer(String referenceAnswer) { this.referenceAnswer = referenceAnswer; }
    public Integer getMaxScore() { return maxScore; }
    public void setMaxScore(Integer maxScore) { this.maxScore = maxScore; }
}

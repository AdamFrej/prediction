package pl.waw.frej.prediction.web.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QuestionForm implements Serializable {
    private String name;
    private String description;

    private String answerOne;
    private String answerTwo;
    private LocalDateTime completionTime;
    private Long completionValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public Long getCompletionValue() {
        return completionValue;
    }

    public void setCompletionValue(Long completionValue) {
        this.completionValue = completionValue;
    }
}

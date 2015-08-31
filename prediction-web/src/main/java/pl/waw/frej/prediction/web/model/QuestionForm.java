package pl.waw.frej.prediction.web.model;

import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Question;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class QuestionForm implements Serializable, Question {
    private Long id;
    private String name;
    private String description;
    private List<Answer> answers;

    private AnswerForm answerOne;
    private AnswerForm answerTwo;
    private Date completionTime;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public AnswerForm getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(AnswerForm answerOne) {
        this.answerOne = answerOne;
    }

    public AnswerForm getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(AnswerForm answerTwo) {
        this.answerTwo = answerTwo;
    }

    @Override
    public Date getCompletionTime() {
        return completionTime;
    }

    @Override
    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }
}

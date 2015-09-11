package pl.waw.frej.prediction.web.model;


import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionStub implements Question {

    private Long id;

    public QuestionStub(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setDescription(String description) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Answer> getAnswers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAnswers(List<Answer> answers) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LocalDateTime getLiquidationDate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLiquidationDate(LocalDateTime completionTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long getLiquidationValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLiquidationValue(Long completionValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getOperator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setOperator(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLiquidated() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLiquidated(boolean liquidated) {
        throw new UnsupportedOperationException();
    }
}

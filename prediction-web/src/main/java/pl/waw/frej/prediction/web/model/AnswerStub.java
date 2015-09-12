package pl.waw.frej.prediction.web.model;

import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class AnswerStub implements Answer {

    private Long id;

    public AnswerStub(Long id) {
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
    public LocalDateTime getLiquidationDate() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLiquidationDate(LocalDateTime completionTime) {
        throw new UnsupportedOperationException();
    }


    @Override
    public List<User> getOwners() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addOwner(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeOwner(User owner) {
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

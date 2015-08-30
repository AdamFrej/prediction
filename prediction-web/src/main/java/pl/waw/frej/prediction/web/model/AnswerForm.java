package pl.waw.frej.prediction.web.model;


import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.User;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AnswerForm implements Serializable, Answer{
    private Long id;
    private String name;
    private String description;
    private LocalDateTime completionTime;
    private User user;

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
    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    @Override
    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}

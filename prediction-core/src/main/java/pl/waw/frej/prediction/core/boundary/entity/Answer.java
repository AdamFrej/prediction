package pl.waw.frej.prediction.core.boundary.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface Answer {

    Long getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    LocalDateTime getCompletionTime();

    void setCompletionTime(LocalDateTime completionTime);

    Question getQuestion();
    void setQuestion(Question question);

    List<User> getOwners();
    void addOwner(User user);

    void removeOwner(User owner);
}

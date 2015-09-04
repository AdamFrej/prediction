package pl.frej.waw.prediction.core.boundary.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface Question {

    Long getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    List<Answer> getAnswers();

    void setAnswers(List<Answer> answers);

    LocalDateTime getCompletionTime();

    void setCompletionTime(LocalDateTime completionTime);

    Long getCompletionValue();

    void setCompletionValue(Long completionValue);

    User getOperator();
    void setOperator(User user);
}

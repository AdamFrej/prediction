package pl.frej.waw.prediction.core.entity;

import java.util.Date;

public interface Answer {

    Long getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    Date getCompletionTime();

    void setCompletionTime(Date completionTime);

    Question getQuestion();
    void setQuestion(Question question);

    User getUser();
    void setUser(User user);
}

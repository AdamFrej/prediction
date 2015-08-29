package pl.frej.waw.prediction.core.entity;

import java.time.LocalDateTime;

public interface Answer {

    Long getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    LocalDateTime getCompletionTime();

    void setCompletionTime(LocalDateTime completionTime);
}

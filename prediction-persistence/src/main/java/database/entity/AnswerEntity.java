package database.entity;

import pl.frej.waw.prediction.core.entity.Answer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnswerEntity implements Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private LocalDateTime completionTime;

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
}

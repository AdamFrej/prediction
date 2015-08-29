package pl.waw.frej.prediction.persistence.database.entity;

import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AnswerEntity implements Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    private String name;
    private String description;
    private LocalDateTime completionTime;

    //@ManyToOne
    ///@JoinColumn(name = "id")
//    private UserEntity user;

    //@ManyToOne
    //@JoinColumn(name = "id")
//    private QuestionEntity question;

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
        return null;
    }

    @Override
    public void setUser(User user) {
//        this.user = (UserEntity)user;
    }
}

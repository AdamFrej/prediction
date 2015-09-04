package pl.waw.frej.prediction.persistence.database.entity;

import com.google.common.collect.Lists;
import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.entity.Question;
import pl.frej.waw.prediction.core.boundary.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class AnswerEntity implements Answer {

    @Id
    @GeneratedValue
    @Column(name = "ANSWER_ID")
    private Long id;

    
    private String name;
    private String description;
    private LocalDateTime completionTime;

    @ManyToMany
    @JoinTable(
            name="ANSWER_USER",
            joinColumns={@JoinColumn(name="ANSWER_ID", referencedColumnName="ANSWER_ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")})
    private List<UserEntity> owners;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private QuestionEntity question;

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
    public List<User> getOwners() {
        return Lists.newArrayList(owners);
    }

    @Override
    public void addOwner(User user) {
        owners.add((UserEntity) user);
    }

    @Override
    public void removeOwner(User owner) {
        owners.remove(owner);
    }

    @Override
    public Question getQuestion() {
        return question;
    }
    @Override
    public void setQuestion(Question question) {
        this.question = (QuestionEntity)question;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AnswerEntity ? this.getId().equals(((AnswerEntity) obj).getId()) : super.equals(obj);
    }
}

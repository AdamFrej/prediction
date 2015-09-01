package pl.waw.frej.prediction.persistence.database.entity;

import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Question;
import pl.frej.waw.prediction.core.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AnswerEntity implements Answer {

    @Id
    @GeneratedValue
    @Column(name = "ANSWER_ID")
    private Long id;

    
    private String name;
    private String description;
    private Date completionTime;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private UserEntity user;

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
    public Date getCompletionTime() {
        return completionTime;
    }

    @Override
    public void setCompletionTime(Date completionTime) {
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

    @Override
    public Question getQuestion() {
        return question;
    }
    @Override
    public void setQuestion(Question question) {
        this.question = (QuestionEntity)question;
    }
}

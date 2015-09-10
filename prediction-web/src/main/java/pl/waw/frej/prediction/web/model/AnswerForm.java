package pl.waw.frej.prediction.web.model;


import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;

import java.io.Serializable;
import java.util.Date;

public class AnswerForm implements Serializable{
    private Long id;
    private String name;
    private String description;
    private Date completionTime;
    private User user;
    private Question question;

    
    public Long getId() {
        return id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    
    public Date getCompletionTime() {
        return completionTime;
    }

    
    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    
    public Question getQuestion() {
        return question;
    }

    
    public void setQuestion(Question question) {
        this.question=question;
    }

    
    public User getUser() {
        return user;
    }

    
    public void setUser(User user) {
        this.user = user;
    }
}

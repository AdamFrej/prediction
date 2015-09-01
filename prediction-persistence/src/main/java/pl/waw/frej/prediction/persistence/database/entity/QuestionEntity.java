package pl.waw.frej.prediction.persistence.database.entity;

import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Question;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class QuestionEntity implements Question {
    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    private Long id;
    private String name;
    private String description;
    private Date completionTime;
    private Long completionValue;

    @OneToMany(mappedBy = "question")
    private List<AnswerEntity> answers;

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
    public List<Answer> getAnswers() {
        return null;
    }

    @Override
    public void setAnswers(List<Answer> answers) {

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
    public Long getCompletionValue() {
        return completionValue;
    }

    @Override
    public void setCompletionValue(Long completionValue) {
        this.completionValue = completionValue;
    }
}

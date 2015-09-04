package pl.waw.frej.prediction.persistence.database.entity;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.entity.Question;
import pl.frej.waw.prediction.core.boundary.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class QuestionEntity implements Question {
    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    private Long id;
    private String name;
    private String description;
    private LocalDateTime completionTime;
    private Long completionValue;
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity operator;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
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
        return Lists.newArrayList(answers);
    }

    @Override
    public void setAnswers(List<Answer> answers) {
        this.answers = Lists.newArrayList(Iterables.<Answer, AnswerEntity>transform(answers, answer -> (AnswerEntity) answer));
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
    public Long getCompletionValue() {
        return completionValue;
    }

    @Override
    public void setCompletionValue(Long completionValue) {
        this.completionValue = completionValue;
    }

    @Override
    public User getOperator() {
        return operator;
    }

    @Override
    public void setOperator(User operator) {
        this.operator = (UserEntity) operator;
    }
}

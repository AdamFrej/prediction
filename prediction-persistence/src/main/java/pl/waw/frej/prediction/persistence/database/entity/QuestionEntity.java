package pl.waw.frej.prediction.persistence.database.entity;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class QuestionEntity implements Question {
    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    private Long id;
    private String name;
    private String description;
    private LocalDateTime liquidationDate;
    private Long liquidationValue;
    private boolean liquidated;
    @ManyToOne
    private UserEntity operator;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="T_QUESTION_ID", referencedColumnName="QUESTION_ID")
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
        return new ArrayList<>(answers);
    }

    @Override
    public void setAnswers(List<Answer> answers) {
        this.answers = Lists.newArrayList(Iterables.<Answer, AnswerEntity>transform(answers, answer -> (AnswerEntity) answer));
    }

    @Override
    public LocalDateTime getLiquidationDate() {
        return liquidationDate;
    }

    @Override
    public void setLiquidationDate(LocalDateTime liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    @Override
    public Long getLiquidationValue() {
        return liquidationValue;
    }

    @Override
    public void setLiquidationValue(Long liquidationValue) {
        this.liquidationValue = liquidationValue;
    }

    @Override
    public User getOperator() {
        return operator;
    }

    @Override
    public void setOperator(User operator) {
        this.operator = (UserEntity) operator;
    }

    @Override
    public boolean isLiquidated() {
        return liquidated;
    }

    @Override
    public void setLiquidated(boolean liquidated) {
        this.liquidated = liquidated;
    }
}

package pl.waw.frej.prediction.core.boundary.entity;

import java.time.LocalDateTime;
import java.util.List;

public interface Question {

    Long getId();

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    List<Answer> getAnswers();

    void setAnswers(List<Answer> answers);

    LocalDateTime getLiquidationDate();

    void setLiquidationDate(LocalDateTime completionTime);

    Long getLiquidationValue();

    void setLiquidationValue(Long completionValue);

    User getOperator();
    void setOperator(User user);

    boolean isLiquidated();

    void setLiquidated(boolean liquidated);
}

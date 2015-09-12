package pl.waw.frej.prediction.web.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class QuestionDetailsForm implements Serializable{
    private String name;
    private String description;
    private LocalDateTime liquidationDate;
    private Long liquidationValue;

    private String answerOneName;
    private String answerTwoName;

    private Double answerOnePercentage;
    private Double answerTwoPercentage;

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

    public LocalDateTime getLiquidationDate() {
        return liquidationDate;
    }

    public void setLiquidationDate(LocalDateTime liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    public Long getLiquidationValue() {
        return liquidationValue;
    }

    public void setLiquidationValue(Long liquidationValue) {
        this.liquidationValue = liquidationValue;
    }

    public String getAnswerOneName() {
        return answerOneName;
    }

    public void setAnswerOneName(String answerOneName) {
        this.answerOneName = answerOneName;
    }

    public String getAnswerTwoName() {
        return answerTwoName;
    }

    public void setAnswerTwoName(String answerTwoName) {
        this.answerTwoName = answerTwoName;
    }

    public Double getAnswerOnePercentage() {
        return answerOnePercentage;
    }

    public void setAnswerOnePercentage(Double answerOnePercentage) {
        this.answerOnePercentage = answerOnePercentage;
    }

    public Double getAnswerTwoPercentage() {
        return answerTwoPercentage;
    }

    public void setAnswerTwoPercentage(Double answerTwoPercentage) {
        this.answerTwoPercentage = answerTwoPercentage;
    }
}

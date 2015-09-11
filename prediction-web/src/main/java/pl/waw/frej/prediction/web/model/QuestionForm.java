package pl.waw.frej.prediction.web.model;

import java.io.Serializable;

public class QuestionForm implements Serializable {
    private String name;
    private String description;

    private String answerOne;
    private String answerTwo;
    private String liquidationDate;
    private Long liquidationValue;

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

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getLiquidationDate() {
        return liquidationDate;
    }

    public void setLiquidationDate(String liquidationDate) {
        this.liquidationDate = liquidationDate;
    }

    public Long getLiquidationValue() {
        return liquidationValue;
    }

    public void setLiquidationValue(Long liquidationValue) {
        this.liquidationValue = liquidationValue;
    }
}

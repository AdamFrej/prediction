package pl.waw.frej.prediction.persistence.collection;

import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.AnswerPrice;

public class PersistentAnswerPrice implements AnswerPrice {

    private Answer answer;

    private Long buyPrice;
    private Long sellPrice;
    private Long averagePrice;

    @Override
    public Answer getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public Long getBuyPrice() {
        return buyPrice;
    }

    @Override
    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Override
    public Long getSellPrice() {
        return sellPrice;
    }

    @Override
    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    @Override
    public Long getAveragePrice() {
        return averagePrice;
    }

    @Override
    public void setAveragePrice(Long averagePrice) {
        this.averagePrice = averagePrice;
    }
}

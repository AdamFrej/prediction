package pl.waw.frej.prediction.core.boundary.entity;


public class Quote {

    private String answerName;
    private Long answerId;

    private Long buyPrice;
    private Long sellPrice;
    private Long lastTransactionPrice;

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Long getLastTransactionPrice() {
        return lastTransactionPrice;
    }

    public void setLastTransactionPrice(Long lastTransactionPrice) {
        this.lastTransactionPrice = lastTransactionPrice;
    }
}

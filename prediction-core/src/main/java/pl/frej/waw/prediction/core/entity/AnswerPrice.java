package pl.frej.waw.prediction.core.entity;


public interface AnswerPrice {
    Answer getAnswer();

    Long getBuyPrice();

    Long getSellPrice();

    Long getAveragePrice();

    void setBuyPrice(Long price);

    void setSellPrice(Long price);

    void setAveragePrice(Long price);
}

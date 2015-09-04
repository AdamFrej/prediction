package pl.frej.waw.prediction.core.boundary.entity;

import java.time.LocalDateTime;

public interface Offer {

    Long getId();

    OfferType getType();

    void setType(OfferType offerType);

    Long getPrice();

    void setPrice(Long price);

    Long getQuantity();

    void setQuantity(Long quantity);

    Answer getAnswer();

    void setAnswer(Answer answer);

    User getUser();

    void setUser(User user);

    LocalDateTime getCreatedDate();

    void setCreatedDate(LocalDateTime createdDate);

    default boolean isValid(){
        boolean hasFunds = getPrice() <= getUser().getFunds();
        Long answerCount = getUser().getAnswerQuantities().get(getAnswer());
        boolean hasAnswers = answerCount != null && getQuantity() <= answerCount;
        return OfferType.BUY.equals(getType()) ? hasFunds : hasAnswers;
    }
}

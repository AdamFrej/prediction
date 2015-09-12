package pl.waw.frej.prediction.core.boundary.entity;

import java.time.LocalDateTime;
import java.util.Map;

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

    default boolean isValid() {
        if(getQuantity()<=0)
            return false;
        boolean hasFunds = getPrice() <= getUser().getFunds();
        Long answerCount = getUser().getAnswerQuantities().get(getAnswer());
        boolean hasAnswers = answerCount != null && getQuantity() <= answerCount;
        return OfferType.BUY.equals(getType()) ? hasFunds : hasAnswers;
    }
}

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
        boolean hasFunds = getPrice() <= getUser().getFunds();
        Long answerCount = null;
        for (Map.Entry<Answer, Long> entry : getUser().getAnswerQuantities().entrySet()) {
            if (entry.getKey().getId().equals(getAnswer().getId())) {
                answerCount = entry.getValue();
                break;
            }
        }
        boolean hasAnswers = answerCount != null && getQuantity() <= answerCount;
        return OfferType.BUY.equals(getType()) ? hasFunds : hasAnswers;
    }
}

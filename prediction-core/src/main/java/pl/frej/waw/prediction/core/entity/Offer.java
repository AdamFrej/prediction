package pl.frej.waw.prediction.core.entity;

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
}

package pl.frej.waw.prediction.core.entity;

import java.time.LocalDateTime;

public interface Transaction {
    Long getId();

    User getUser();

    void setAuthor(User user);

    Answer getAnswer();

    void setAnswer(Answer answer);

    Long getPrice();

    void setPrice(Long price);

    Long getQuantity();

    void setQuantity(Long quantity);

    User getBuyer();

    void setBuyer(User buyer);

    User getSeller();

    void setSeller(User seller);

    LocalDateTime getCompletionDate();

    void setCompletionDate(LocalDateTime completionDate);
}

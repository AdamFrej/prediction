package pl.frej.waw.prediction.core.entity;

import java.time.LocalDateTime;
import java.util.Date;

public interface Transaction {
    Long getId();
    User getUser();
    void setUser(User user);

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

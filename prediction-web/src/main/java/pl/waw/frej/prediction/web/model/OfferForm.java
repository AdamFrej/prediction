package pl.waw.frej.prediction.web.model;

import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;
import pl.frej.waw.prediction.core.entity.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class OfferForm implements Serializable, Offer {

    private Long id;
    private OfferType offerType;
    private Long price;
    private Long quantity;
    private Long answerId;
    private Long userId;
    private LocalDateTime createdDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public OfferType getType() {
        return offerType;
    }

    @Override
    public void setType(OfferType offerType) {
        this.offerType = offerType;
    }

    @Override
    public Long getPrice() {
        return price;
    }

    @Override
    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public Long getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswer(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public Answer getAnswer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAnswer(Answer answer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void setUser(User user) {
        this.userId = user.getId();
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}

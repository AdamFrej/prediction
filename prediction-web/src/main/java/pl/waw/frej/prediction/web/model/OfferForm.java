package pl.waw.frej.prediction.web.model;

import pl.frej.waw.prediction.core.boundary.entity.OfferType;

import java.io.Serializable;

public class OfferForm implements Serializable {

    private OfferType type;
    private Long price;
    private Long quantity;
    private Long answerId;
    private Long userId;

    public OfferType getType() {
        return type;
    }

    public void setType(OfferType type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

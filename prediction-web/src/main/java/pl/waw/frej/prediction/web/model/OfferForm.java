package pl.waw.frej.prediction.web.model;

import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;

import java.io.Serializable;

public class OfferForm implements Serializable,Offer {

    private Long id;
    private OfferType offerType;
    private Long price;
    private Long quantity;
    private Long answerId;

    public Long getId() {
        return id;
    }

    @Override
    public OfferType getOfferType() {
        return offerType;
    }

    @Override
    public void setOfferType(OfferType offerType) {
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

    @Override
    public Long getAnswerId() {
        return answerId;
    }

    @Override
    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}

package pl.waw.frej.prediction.web.model;

import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;

import java.io.Serializable;

public class OfferForm implements Serializable,Offer {

    private Long id;
    private OfferType offerType;
    private Integer price;
    private Integer quantity;
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
    public Integer getPrice() {
        return price;
    }

    @Override
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(Integer quantity) {
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

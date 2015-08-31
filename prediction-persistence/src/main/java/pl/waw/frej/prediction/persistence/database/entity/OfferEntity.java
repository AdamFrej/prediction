package pl.waw.frej.prediction.persistence.database.entity;


import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;

import javax.persistence.*;

@Entity
public class OfferEntity implements Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @Enumerated(EnumType.STRING)
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

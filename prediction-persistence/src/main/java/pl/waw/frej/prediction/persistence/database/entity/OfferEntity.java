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

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}

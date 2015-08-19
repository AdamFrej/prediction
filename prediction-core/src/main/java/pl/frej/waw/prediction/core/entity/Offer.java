package pl.frej.waw.prediction.core.entity;

public class Offer {
    private OfferType offerType;
    private Integer price;
    private Integer quantity;
    private String answerId;

    public Offer(OfferType offerType, Integer price, Integer quantity, String answerId) {
        this.offerType = offerType;
        this.price = price;
        this.quantity = quantity;
        this.answerId = answerId;
    }

    public OfferType getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}

package pl.frej.waw.prediction.core.entity;

 public interface Offer {

     OfferType getOfferType();

     void setOfferType(OfferType offerType);

     Integer getPrice();

     void setPrice(Integer price);

     Integer getQuantity();

     void setQuantity(Integer quantity);

     Long getAnswerId();

     void setAnswerId(Long answerId);
 }

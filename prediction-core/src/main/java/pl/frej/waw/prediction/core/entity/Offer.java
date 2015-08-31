package pl.frej.waw.prediction.core.entity;

 public interface Offer {

     OfferType getOfferType();

     void setOfferType(OfferType offerType);

     Long getPrice();

     void setPrice(Long price);

     Long getQuantity();

     void setQuantity(Long quantity);

     Long getAnswerId();

     void setAnswerId(Long answerId);
 }

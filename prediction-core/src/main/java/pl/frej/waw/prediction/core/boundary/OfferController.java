package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;

import java.util.List;

public interface OfferController {
    boolean add(String questionId, String answerId, OfferType type, Integer price,
        Integer quantity);

    List<Offer> find(String UserId);

    boolean cancel(String OfferId);
}

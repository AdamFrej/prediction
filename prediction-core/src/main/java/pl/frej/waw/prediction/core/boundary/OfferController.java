package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Offer;

import java.util.List;

public interface OfferController {
    boolean add(String questionId, Offer offer, String UserId);

    List<Offer> find(String userId);

    List<Offer> findByAnswer(String answerId);

    boolean cancel(String offerId);
}

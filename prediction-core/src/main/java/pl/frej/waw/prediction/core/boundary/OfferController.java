package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Offer;

import java.util.List;

public interface OfferController {
    boolean add(String questionId, Offer offer, String UserId);

    List<Offer> find(Long userId);

    List<Offer> findByAnswer(Long answerId);

    boolean cancel(Long offerId);
}

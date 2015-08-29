package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Offer;

import java.util.List;

public interface OfferController {
    boolean add(Long questionId, Offer offer, Long UserId);

    List<Offer> find(Long userId);

    List<Offer> findByAnswer(Long answerId);

    boolean cancel(Long offerId);
}

package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;

import java.util.List;
import java.util.Optional;

public interface OfferController {
    boolean add(Offer offer, Long UserId);

    List<Offer> find(Long userId);

    List<Offer> findByAnswer(Long answerId);

    boolean cancel(Long offerId);

    boolean isValid(Offer offer, Long userId);
    boolean isValid(Offer offer);

    Optional<Offer> findBestOffer(Answer answer, OfferType offerType);

    void update(List<Offer> offers);
}

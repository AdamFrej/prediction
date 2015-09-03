package pl.frej.waw.prediction.core.persistence;

import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;
import pl.frej.waw.prediction.core.entity.User;

import java.util.List;
import java.util.Optional;

public interface Offers {
    boolean add(Offer offer);

    boolean remove(Long id);

    Offer find(Long Id);

    List<Offer> findByUser(User user);

    List<Offer> findByAnswer(Long answerId);
    List<Offer> findByAnswerAndType(Long answerId, OfferType offerType);

    Offer update(Offer offer);

    default void update(List<Offer> offers) {
        for (Offer offer : offers) {
            if (offer.getQuantity() == 0)
                remove(offer.getId());
            else
                update(offer);
        }
    }

    default Optional<Offer> findBestOffer(Answer answer, OfferType offerType) {
        List<Offer> offers = findByAnswerAndType(answer.getId(), offerType);
        return Optional.ofNullable(offers.isEmpty() ? null : offers.get(0));
    }
}

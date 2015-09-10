package pl.waw.frej.prediction.core.boundary.collection;

import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Offer;
import pl.waw.frej.prediction.core.boundary.entity.OfferType;
import pl.waw.frej.prediction.core.boundary.entity.User;

import java.util.List;
import java.util.Optional;

public interface Offers {
    boolean add(Offer offer);

    boolean remove(Long id);

    Optional<Offer> find(Long Id);

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

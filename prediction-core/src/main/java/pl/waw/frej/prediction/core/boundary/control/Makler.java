package pl.waw.frej.prediction.core.boundary.control;

import pl.waw.frej.prediction.core.boundary.entity.Offer;
import pl.waw.frej.prediction.core.boundary.entity.Quote;
import pl.waw.frej.prediction.core.boundary.entity.User;

import java.util.List;
import java.util.Optional;

public interface Makler {
    boolean addOffer(Offer offer, User user);

    Optional<Offer> findOffer(Long id);

    List<Offer> findOffers(User user);

    boolean cancel(User user, Long offerId);

    void buyBundle(Long questionId, Long quantity, User user);

    List<Quote> findQuotes();
    Optional<Quote> findQuote(Long id);
}

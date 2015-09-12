package pl.waw.frej.prediction.core.boundary.control;

import pl.waw.frej.prediction.core.boundary.entity.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Makler {
    boolean addOffer(Offer offer, User user);

    Optional<Offer> findOffer(Long id);

    List<Offer> findOffers(User user);
    List<Transaction> findTransactions(User user);

    boolean cancel(User user, Long offerId);

    void buyBundle(Long questionId, Long quantity, User user);

    List<Quote> findQuotes();
    Optional<Quote> findQuote(Long id);

    Long findFunds(User user);

    Map<Answer,Long> getAnswerQuantities(User user);
}

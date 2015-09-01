package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.OfferController;
import pl.frej.waw.prediction.core.boundary.TransactionController;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Offers;
import pl.frej.waw.prediction.core.persistence.Users;

import java.util.List;

public class SimpleOfferController implements OfferController {

    private final Offers offers;
    private final Users users;
    private final TransactionController transactionController;

    public SimpleOfferController(Offers offers, Users users, TransactionController transactionController) {
        this.offers = offers;
        this.users = users;
        this.transactionController = transactionController;
    }

    @Override public boolean add(Offer offer, Long userId) {
        if (isValid(offer, userId)) {
            offers.add(offer);
            transactionController.make();
            return true;
        } else {
            return false;
        }
    }

    @Override public List<Offer> find(Long userId) {
        return offers.findByUser(userId);
    }

    @Override public List<Offer> findByAnswer(Long answerId) {
        return offers.findByAnswer(answerId);
    }

    @Override public boolean cancel(Long OfferId) {
        offers.remove(OfferId);
        return true;
    }

    private boolean isValid(Offer offer, Long userId) {
        User user = users.find(userId);

        switch (offer.getOfferType()) {
            case BUY:
                return hasFundsForAtLeastOneAnswer(offer, user);
            case SELL:
                return hasAnswerInSpecifiedQuantity(offer, user);
            default:
                return false;
        }
    }

    private boolean hasAnswerInSpecifiedQuantity(Offer offer, User user) {
        return false;//offer.getQuantity().compareTo(user.getAnswerQuantities().get(offer.getAnswerId())) < 1;
    }

    private boolean hasFundsForAtLeastOneAnswer(Offer offer, User user) {
        return offer.getPrice().compareTo(user.getFunds()) < 1;
    }

}

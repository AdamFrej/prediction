package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.OfferController;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Offers;
import pl.frej.waw.prediction.core.persistence.Users;

import java.util.List;

public class SimpleOfferController implements OfferController {

    private final Offers offers;
    private final Users users;

    public SimpleOfferController(Offers offers, Users users) {
        this.offers = offers;
        this.users = users;
    }

    @Override public boolean add(String questionId, Offer offer, String userId) {
        if (isValid(offer, userId)) {
            offers.add(offer);
            return true;
        } else {
            return false;
        }
    }

    @Override public List<Offer> find(String userId) {
        return offers.findByUser(userId);
    }

    @Override public List<Offer> findByAnswer(String answerId) {
        return offers.findByAnswer(answerId);
    }

    @Override public boolean cancel(String OfferId) {
        offers.remove(OfferId);
        return true;
    }

    private boolean isValid(Offer offer, String userId) {
        User user = users.find(userId);

        switch (offer.getOfferType()) {
            case BUY:
                return hasFundsForAtLeastOneAnswer(offer, user);
            case SELl:
                return hasAnswerInSpecifiedQuantity(offer, user);
            default:
                return false;
        }
    }

    private boolean hasAnswerInSpecifiedQuantity(Offer offer, User user) {
        return offer.getQuantity().compareTo(user.getAnswerQuantities().get(offer.getAnswerId())) < 1;
    }

    private boolean hasFundsForAtLeastOneAnswer(Offer offer, User user) {
        return offer.getPrice().compareTo(user.getFunds()) < 1;
    }

}

package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.OfferController;
import pl.frej.waw.prediction.core.boundary.TransactionController;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Offers;
import pl.frej.waw.prediction.core.persistence.Users;

import java.util.List;
import java.util.Optional;

public class SimpleOfferController implements OfferController {

    private final Offers offers;
    private final Users users;
    private final TransactionController transactionController;

    public SimpleOfferController(Offers offers, Users users, TransactionController transactionController) {
        this.offers = offers;
        this.users = users;
        this.transactionController = transactionController;
    }

    @Override
    public boolean add(Offer offer, Long userId) {
        if (isValid(offer, userId)) {
            offers.add(offer);
            transactionController.make(offer, userId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Offer> find(Long userId) {
        return offers.findByUser(userId);
    }

    @Override
    public List<Offer> findByAnswer(Long answerId) {
        return offers.findByAnswer(answerId);
    }

    @Override
    public boolean cancel(Long OfferId) {
        offers.remove(OfferId);
        return true;
    }

    @Override
    public boolean isValid(Offer offer, Long userId) {

        Optional<User> user = users.find(userId);

        boolean hasFunds = offer.getPrice() <= user.get().getFunds();
        boolean hasAnswers = offer.getQuantity() <= user.get().getAnswerQuantities().get(offer.getAnswer());
        return user.isPresent() && OfferType.BUY.equals(offer.getType()) ? hasFunds : hasAnswers;
    }


    @Override
    public boolean isValid(Offer offer) {
        return isValid(offer, offer.getUser().getId());
    }

    @Override
    public Optional<Offer> findBestOffer(Answer answer, OfferType offerType) {
        List<Offer> offers = this.offers.findByAnswerAndType(answer.getId(), offerType);
        return Optional.ofNullable(offers.isEmpty() ? null : offers.get(0));
    }

    @Override
    public void update(List<Offer> offers) {
        for (Offer offer : offers) {
            if (offer.getQuantity() == 0)
                this.offers.remove(offer.getId());
            else
                this.offers.update(offer);
        }
    }

}

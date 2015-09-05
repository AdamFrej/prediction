package pl.frej.waw.prediction.core.usecase;


import pl.frej.waw.prediction.core.boundary.control.Makler;
import pl.frej.waw.prediction.core.boundary.entity.Offer;
import pl.frej.waw.prediction.core.boundary.entity.Question;
import pl.frej.waw.prediction.core.boundary.entity.Quote;
import pl.frej.waw.prediction.core.boundary.entity.User;
import pl.frej.waw.prediction.core.boundary.collection.*;
import pl.frej.waw.prediction.core.operations.Bundle;
import pl.frej.waw.prediction.core.operations.Market;
import pl.frej.waw.prediction.core.operations.Transfer;

import java.util.List;
import java.util.Optional;

public class MaklerImpl implements Makler {

    private final Questions questions;
    private final Offers offers;

    private final Bundle bundle;
    private final Transfer transfer;
    private final Market market;


    public MaklerImpl(Answers answers, Questions questions, Offers offers, Users users, Transactions transactions) {
        this.questions = questions;
        this.offers = offers;
        this.transfer = new Transfer(users, offers, transactions);
        this.bundle = new Bundle(users, transactions);
        this.market = new Market(answers, offers);
    }

    @Override
    public boolean addOffer(Offer offer, User user) {
        if (!offer.isValid()) {
            return false;
        }
        if (offers.add(offer))
            makeTransferIfPossible(offer, user);
        return false;
    }

    @Override
    public Optional<Offer> findOffer(Long id) {
        return offers.find(id);
    }
    @Override
    public List<Offer> findOffers(User user) {
        return offers.findByUser(user);
    }

    @Override
    public boolean cancel(User user, Long offerId) {
        return user.getId().equals(offerId) && offers.remove(offerId);
    }

    @Override
    public void buyBundle(Long questionId, Long quantity, User user) {
        Optional<Question> question = questions.findOne(questionId);
        if (question.isPresent())
            bundle.buy(user, question.get(), quantity);
    }

    @Override
    public List<Quote> findQuotes() {
        return market.findQuotes();
    }

    private void makeTransferIfPossible(Offer offer, User user) {
        Optional<Offer> otherOffer = offers.findBestOffer(offer.getAnswer(), offer.getType().flip());
        if (otherOffer.isPresent() && otherOffer.get().isValid()) {
            transfer.make(offer, otherOffer.get(), user);
        }
    }
}

package pl.waw.frej.prediction.core.usecase;


import pl.waw.frej.prediction.core.boundary.collection.*;
import pl.waw.frej.prediction.core.boundary.control.Makler;
import pl.waw.frej.prediction.core.boundary.entity.*;
import pl.waw.frej.prediction.core.operations.Bundle;
import pl.waw.frej.prediction.core.operations.Market;
import pl.waw.frej.prediction.core.operations.Transfer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MaklerImpl implements Makler {

    private final Questions questions;
    private final Offers offers;
    private final Answers answers;
    private final Transactions transactions;
    private final Users users;

    private final Bundle bundle;
    private final Transfer transfer;
    private final Market market;


    public MaklerImpl(Answers answers, Questions questions, Offers offers, Users users, Transactions transactions) {
        this.questions = questions;
        this.offers = offers;
        this.transfer = new Transfer(users, offers, transactions);
        this.bundle = new Bundle(users, transactions, answers);
        this.market = new Market(answers, offers, transactions);
        this.answers = answers;
        this.transactions = transactions;
        this.users = users;
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
    public List<Transaction> findTransactions(User user) {
        List<Transaction> transactions = this.transactions.findByBuyer(user);
        transactions.addAll(this.transactions.findBySeller(user));
        return transactions;
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

    @Override
    public Optional<Quote> findQuote(Long id) {
        Optional<Answer> answer = answers.find(id);
        Quote quote = answer.isPresent() ? market.findQuote(answer.get()) : null;
        return Optional.ofNullable(quote);
    }

    @Override
    public Long findFunds(User user) {
        Optional<User> u = users.find(user.getId());
        if(u.isPresent())
            return u.get().getFunds();
        return null;
    }

    @Override
    public Map<Answer, Long> getAnswerQuantities(User user) {
        return users.find(user.getId()).get().getAnswerQuantities();
    }

    private void makeTransferIfPossible(Offer offer, User user) {
        Optional<Offer> otherOffer = offers.findBestOffer(offer.getAnswer(), offer.getType().flip());
        if (otherOffer.isPresent() && otherOffer.get().isValid()) {
            transfer.make(offer, otherOffer.get(), user);
        }
    }
}

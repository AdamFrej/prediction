package pl.frej.waw.prediction.core.usecase;


import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.Question;
import pl.frej.waw.prediction.core.entity.Quote;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.operations.Bundle;
import pl.frej.waw.prediction.core.operations.Market;
import pl.frej.waw.prediction.core.operations.Transfer;
import pl.frej.waw.prediction.core.persistence.*;

import java.util.List;
import java.util.Optional;

public class Makler {

    private final Questions questions;
    private final Offers offers;
    private final Users users;
    private final Transactions transactions;
    private final Bundle bundle;
    private final Transfer transfer;
    private final Market market;


    public Makler(Answers answers, Questions questions, Offers offers, Users users, Transactions transactions) {
        this.questions = questions;
        this.offers = offers;
        this.users = users;
        this.transactions = transactions;
        this.transfer = new Transfer(users, offers, transactions);
        this.bundle = new Bundle(this.users, this.transactions);
        this.market = new Market(answers,offers);
    }

    public List<Question> read() {
        return questions.find();
    }

    public void addOffer(Offer offer, User user) {
        if (!offer.isValid()) {
            return;
        }
        offers.add(offer);
        makeTransferIfPossible(offer, user);
    }

    public List<Offer> findOffers(User user){
        return offers.findByUser(user);
    }

    public void buyBundle(Long questionId, Long quantity, User user){
        Optional<Question> question = questions.find(questionId);
        if(question.isPresent())
            bundle.buy(user, question.get(), quantity);
    }

    public List<Quote> findQuotes(){
        return market.findQuotes();
    }

    private void makeTransferIfPossible(Offer offer, User user) {
        Optional<Offer> otherOffer = offers.findBestOffer(offer.getAnswer(), offer.getType().flip());
        if(otherOffer.isPresent() && otherOffer.get().isValid()){
            transfer.make(offer, otherOffer.get(), user);
        }
    }

}

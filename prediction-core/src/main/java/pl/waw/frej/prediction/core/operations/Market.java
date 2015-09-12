package pl.waw.frej.prediction.core.operations;

import pl.waw.frej.prediction.core.boundary.collection.Transactions;
import pl.waw.frej.prediction.core.boundary.entity.*;
import pl.waw.frej.prediction.core.boundary.collection.Answers;
import pl.waw.frej.prediction.core.boundary.collection.Offers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Market {

    private final Answers answers;
    private final Offers offers;
    private final Transactions transactions;

    public Market(Answers answers, Offers offers, Transactions transactions) {
        this.answers = answers;
        this.offers = offers;
        this.transactions = transactions;
    }

    public List<Quote> findQuotes() {
        return answers.findAll().stream().map(this::findQuote).collect(Collectors.toList());
    }

    public Quote findQuote(Answer answer) {
        Quote q = new Quote();
        q.setAnswerName(answer.getName());
        q.setAnswerId(answer.getId());
        q.setBuyPrice(getPrice(answer, OfferType.BUY).orElse(null));
        q.setSellPrice(getPrice(answer, OfferType.SELL).orElse(null));
        q.setLastTransactionPrice(getLastTransactionPrice(answer).orElse(null));
        return q;
    }

    private Optional<Long> getLastTransactionPrice(Answer answer) {
        Optional<Transaction> first = transactions.find(answer).stream().findFirst();
        Optional<Long> price = Optional.empty();

        if(first.isPresent())
             price = Optional.of(first.get().getPrice());

        return price;
    }

    private Optional<Long> getPrice(Answer answer, OfferType type) {
        List<Offer> offers = this.offers.findByAnswerAndType(answer.getId(), type);
        return Optional.ofNullable(offers.isEmpty() ? null : offers.get(0).getPrice());
    }
}

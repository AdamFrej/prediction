package pl.frej.waw.prediction.core.usecase;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import pl.frej.waw.prediction.core.boundary.TransactionController;
import pl.frej.waw.prediction.core.entity.*;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.frej.waw.prediction.core.persistence.Offers;
import pl.frej.waw.prediction.core.persistence.Transactions;

import java.util.Comparator;
import java.util.List;

public class SimpleTransactionController implements TransactionController {
    private static final Comparator<Offer> BY_PRICE = (o1, o2) -> o1.getPrice().compareTo(o2.getPrice());
    private static final Predicate<Offer> BUY = offer -> OfferType.BUY.equals(offer.getOfferType());
    private static final Predicate<Offer> SELL = offer -> OfferType.SELL.equals(offer.getOfferType());
    private final Transactions transactions;
    private final Offers offers;
    private final Answers answers;

    private EntityFactory entityFactory;

    public SimpleTransactionController(Transactions transactions, Offers offers, Answers answers) {
        this.transactions = transactions;
        this.offers = offers;
        this.answers = answers;
    }

    @Override public List<Transaction> find(String userId) {
        return transactions.find();
    }

    @Override public void make() {
        for (Answer answer : answers.findAll()) {
            List<Offer> o = offers.findByAnswer(answer.getId());
            List<Offer> buyOffers = Ordering.from(BY_PRICE).reverse().sortedCopy(Iterables.filter(o, BUY));
            List<Offer> sellOffers = Ordering.from(BY_PRICE).sortedCopy(Iterables.filter(o, SELL));
            if (transactionIsAvailable(buyOffers, sellOffers)) {
                Transaction transaction = entityFactory.createTransaction();
                transactions.add(transaction);

            }
        }
    }

    private boolean transactionIsAvailable(List<Offer> buyOffers, List<Offer> sellOffers) {
        return buyOffers.get(0).getPrice() >= sellOffers.get(0).getPrice();
    }
}

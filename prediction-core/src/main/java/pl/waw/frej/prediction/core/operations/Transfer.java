package pl.waw.frej.prediction.core.operations;

import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Offer;
import pl.waw.frej.prediction.core.boundary.entity.Transaction;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.core.boundary.collection.Offers;
import pl.waw.frej.prediction.core.boundary.collection.Transactions;
import pl.waw.frej.prediction.core.boundary.collection.Users;
import pl.waw.frej.prediction.core.boundary.entity.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transfer {

    private Offer buy;
    private Offer sell;
    private User buyer;
    private User seller;
    private User author;
    private Long price;
    private Answer answer;
    private Long quantity;

    private final Users users;
    private final Offers offers;
    private final Transactions transactions;

    public Transfer(Users users, Offers offers, Transactions transactions) {
        this.users = users;
        this.offers = offers;
        this.transactions = transactions;
    }

    public void make(Offer a, Offer b, User user) {
        if(offersAreValidForTransaction(a, b)){

            buy = a.getType() == OfferType.BUY ? a : b;
            sell = a.getType() == OfferType.SELL ? a :b;
            buyer = buy.getUser();
            seller = sell.getUser();
            author=user;
            price = a.getCreatedDate().isBefore(b.getCreatedDate()) ? a.getPrice() : b.getPrice();

            answer = a.getAnswer();
            quantity = 0L;


            while(buy.isValid() && sell.isValid()) {
                exchangeFundsForAnswers();
                updateOffers();
                quantity++;
            }

            if(quantity >0){
                addTransaction();
            }
        }
    }

    private void addTransaction() {
        Transaction t = transactions.create();
        t.setAuthor(author);
        t.setPrice(price);
        t.setQuantity(quantity);
        t.setAnswer(answer);
        t.setBuyer(buyer);
        t.setSeller(seller);
        t.setCompletionDate(LocalDateTime.now());
        transactions.add(t);
    }

    private void updateOffers() {
        buy.setQuantity(buy.getQuantity() - 1L);
        sell.setQuantity(sell.getQuantity() - 1L);

        List<Offer> o = new ArrayList<>();
        o.add(buy);
        o.add(sell);
        offers.update(o);
    }

    private void exchangeFundsForAnswers() {
        buyer.addAnswer(answer);
        seller.removeOneAnswer(answer);

        buyer.setFunds(buyer.getFunds() - price);
        seller.setFunds(seller.getFunds() + price);

        List<User> u = new ArrayList<>();
        u.add(buyer);
        u.add(seller);
        users.update(u);
    }

    private boolean offersAreValidForTransaction(Offer a, Offer b) {
        boolean offersAreFromTheSameUser = a.getUser().getId().equals(b.getUser().getId());
        boolean offersAreOfTheSameType = a.getType().equals(b.getType());

        buy = a.getType() == OfferType.BUY ? a : b;
        sell = a.getType() == OfferType.SELL ? a :b;

        boolean buPriceIsLoweThanSellPrice = buy.getPrice() < sell.getPrice();

        return !(offersAreFromTheSameUser || offersAreOfTheSameType || buPriceIsLoweThanSellPrice);
    }
}

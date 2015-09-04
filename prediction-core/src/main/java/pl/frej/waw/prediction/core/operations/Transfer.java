package pl.frej.waw.prediction.core.operations;

import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.entity.Offer;
import pl.frej.waw.prediction.core.boundary.entity.Transaction;
import pl.frej.waw.prediction.core.boundary.entity.User;
import pl.frej.waw.prediction.core.boundary.persistence.Offers;
import pl.frej.waw.prediction.core.boundary.persistence.Transactions;
import pl.frej.waw.prediction.core.boundary.persistence.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static pl.frej.waw.prediction.core.boundary.entity.OfferType.BUY;
import static pl.frej.waw.prediction.core.boundary.entity.OfferType.SELL;

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

            buy = a.getType() == BUY ? a : b;
            sell = a.getType() == SELL ? a :b;
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
        return !(offersAreFromTheSameUser || offersAreOfTheSameType);
    }
}

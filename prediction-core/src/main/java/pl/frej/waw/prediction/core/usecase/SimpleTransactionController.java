package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.AnswerController;
import pl.frej.waw.prediction.core.boundary.OfferController;
import pl.frej.waw.prediction.core.boundary.TransactionController;
import pl.frej.waw.prediction.core.entity.*;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.frej.waw.prediction.core.persistence.Transactions;
import pl.frej.waw.prediction.core.persistence.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.frej.waw.prediction.core.entity.OfferType.*;

public class SimpleTransactionController implements TransactionController {
    private final Transactions transactions;
    private final OfferController offerController;
    private final Users users;

    private EntityFactory entityFactory;

    public SimpleTransactionController(Transactions transactions, OfferController offerController, Users users, EntityFactory entityFactory) {
        this.transactions = transactions;
        this.offerController = offerController;
        this.users = users;
        this.entityFactory = entityFactory;
    }

    @Override public List<Transaction> find(String userId) {
        return transactions.find();
    }

    @Override
    public void buyBundle(User user, Question question, Long quantity) {
        Long price = question.getCompletionValue() * quantity;
        List<Answer> answers = question.getAnswers();

        if (price > user.getFunds()) {
            return;
        }

        for (Answer answer : answers) {
            user.addAnswer(answer, quantity);
        }
        user.setFunds(user.getFunds() - price);

        User updated = users.update(user);
        if (updated.getFunds().compareTo(user.getFunds()) == 0) {
            for (Answer answer : answers) {
                addTransaction(user, user, question.getOperator(), price/answers.size(), answer, quantity);
            }
        }
    }


    @Override
    public void make(Offer offer, Long userId) {
        Optional<User> user = users.find(userId);
        Optional<Offer> otherOffer = offerController.findBestOffer(offer.getAnswer(), offer.getType().flip());
        if(user.isPresent() && otherOffer.isPresent() && offerController.isValid(otherOffer.get())){
            complete(offer, otherOffer.get(), user.get());
        }
    }

    private void complete(Offer a, Offer b, User user) {
        if(offersAreValidForTransaction(a, b)){

            Offer buy = a.getType() == BUY ? a : b;
            Offer sell = a.getType() == SELL ? a : b;
            User buyer = buy.getUser();
            User seller = sell.getUser();
            Long price = a.getCreatedDate().isBefore(b.getCreatedDate()) ? a.getPrice() : b.getPrice();
            Answer answer = a.getAnswer();
            Long quantity=0L;


            while(offerController.isValid(buy)&& offerController.isValid(sell)) {
                List<User> u = setupUsers(buyer, seller, price, answer);
                List<Offer> o = setupOffers(buy, sell);

                users.update(u);
                offerController.update(o);
                quantity++;
            }

            if(quantity>0){
                addTransaction(user, buyer, seller, price, answer, quantity);
            }
        }
    }

    private void addTransaction(User user, User buyer, User seller, Long price, Answer answer, Long quantity) {
        Transaction t = entityFactory.createTransaction();
        t.setUser(user);
        t.setPrice(price);
        t.setQuantity(quantity);
        t.setAnswer(answer);
        t.setBuyer(buyer);
        t.setSeller(seller);
        t.setCompletionDate(LocalDateTime.now());
        transactions.add(t);
    }

    private List<Offer> setupOffers(Offer buy, Offer sell) {
        buy.setQuantity(buy.getQuantity() - 1L);
        sell.setQuantity(sell.getQuantity() - 1L);

        List<Offer> o = new ArrayList<>();
        o.add(buy);
        o.add(sell);
        return o;
    }

    private List<User> setupUsers(User buyer, User seller, Long price, Answer answer) {
        buyer.addAnswer(answer);
        seller.removeAnswer(answer);

        buyer.setFunds(buyer.getFunds() - price);
        seller.setFunds(seller.getFunds() + price);

        List<User> u = new ArrayList<>();
        u.add(buyer);
        u.add(seller);
        return u;
    }

    private boolean offersAreValidForTransaction(Offer a, Offer b) {
        boolean offersAreFromTheSameUser = a.getUser().getId().equals(b.getUser().getId());
        boolean offersAreOfTheSameType = a.getType().equals(b.getType());
        return !(offersAreFromTheSameUser || offersAreOfTheSameType);
    }
}

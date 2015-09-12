package pl.waw.frej.prediction.core.boundary.collection;

import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Transaction;
import pl.waw.frej.prediction.core.boundary.entity.User;

import java.util.List;

public interface Transactions {
    List<Transaction> find();
    List<Transaction> find(Answer answer);

    void add(Transaction transaction);

    Transaction create();

    List<Transaction> findByBuyer(User user);

    List<Transaction> findBySeller(User user);
}

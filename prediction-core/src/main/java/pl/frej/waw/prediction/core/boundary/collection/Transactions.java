package pl.frej.waw.prediction.core.boundary.collection;

import pl.frej.waw.prediction.core.boundary.entity.Transaction;

import java.util.List;

public interface Transactions {
    List<Transaction> find();

    void add(Transaction transaction);

    Transaction create();
}

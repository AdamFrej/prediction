package pl.frej.waw.prediction.core.persistence;

import pl.frej.waw.prediction.core.entity.Transaction;

import java.util.List;

public interface Transactions {
    List<Transaction> find();
}

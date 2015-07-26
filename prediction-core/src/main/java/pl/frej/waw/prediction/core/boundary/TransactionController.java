package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Transaction;

import java.util.List;

public interface TransactionController {
    List<Transaction> find(String userId);
}

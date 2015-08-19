package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.TransactionController;
import pl.frej.waw.prediction.core.entity.Transaction;
import pl.frej.waw.prediction.core.persistence.Transactions;

import java.util.List;

public class SimpleTransactionController implements TransactionController {
    Transactions transactions;

    public SimpleTransactionController(Transactions transactions) {
        this.transactions = transactions;
    }

    @Override public List<Transaction> find(String userId) {
        return transactions.find();
    }
}

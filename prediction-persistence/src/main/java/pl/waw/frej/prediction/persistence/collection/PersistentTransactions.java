package pl.waw.frej.prediction.persistence.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.frej.prediction.core.boundary.collection.Transactions;
import pl.waw.frej.prediction.core.boundary.entity.Transaction;
import pl.waw.frej.prediction.persistence.database.entity.TransactionEntity;
import pl.waw.frej.prediction.persistence.database.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class PersistentTransactions implements Transactions {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> find() {
        return new ArrayList<>(transactionRepository.findAll());
    }

    @Override
    public void add(Transaction transaction) {
        transactionRepository.save((TransactionEntity) transaction);
    }

    @Override
    public Transaction create() {
        return new TransactionEntity();
    }
}

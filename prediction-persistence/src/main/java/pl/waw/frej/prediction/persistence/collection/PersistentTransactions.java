package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.frej.waw.prediction.core.boundary.entity.Transaction;
import pl.frej.waw.prediction.core.boundary.persistence.Transactions;
import pl.waw.frej.prediction.persistence.database.entity.TransactionEntity;
import pl.waw.frej.prediction.persistence.database.repository.TransactionRepository;

import java.util.List;

@Component
public class PersistentTransactions implements Transactions {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> find() {
        return Lists.newArrayList(transactionRepository.findAll());
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

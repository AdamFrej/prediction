package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.entity.Transaction;
import pl.frej.waw.prediction.core.persistence.Transactions;
import pl.waw.frej.prediction.persistence.database.entity.TransactionEntity;
import pl.waw.frej.prediction.persistence.database.repository.TransactionRepository;

import java.util.List;

@Service
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

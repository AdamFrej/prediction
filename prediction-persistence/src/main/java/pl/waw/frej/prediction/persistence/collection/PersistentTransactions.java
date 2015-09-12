package pl.waw.frej.prediction.persistence.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.frej.prediction.core.boundary.collection.Transactions;
import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Transaction;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import pl.waw.frej.prediction.persistence.database.entity.TransactionEntity;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
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
    public List<Transaction> find(Answer answer) {
        return new ArrayList<>(transactionRepository.findByAnswerOrderByCompletionDateDesc((AnswerEntity) answer));
    }

    @Override
    public void add(Transaction transaction) {
        transactionRepository.saveAndFlush((TransactionEntity) transaction);
    }

    @Override
    public Transaction create() {
        return new TransactionEntity();
    }

    @Override
    public List<Transaction> findByBuyer(User user) {
        return new ArrayList<>(transactionRepository.findByBuyer((UserEntity)user));
    }

    @Override
    public List<Transaction> findBySeller(User user) {
        return new ArrayList<>(transactionRepository.findBySeller((UserEntity)user));
    }
}

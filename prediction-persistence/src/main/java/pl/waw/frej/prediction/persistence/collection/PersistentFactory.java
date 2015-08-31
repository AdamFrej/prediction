package pl.waw.frej.prediction.persistence.collection;

import pl.frej.waw.prediction.core.entity.AnswerPrice;
import pl.frej.waw.prediction.core.entity.EntityFactory;
import pl.frej.waw.prediction.core.entity.Transaction;
import pl.waw.frej.prediction.persistence.database.entity.TransactionEntity;

public class PersistentFactory implements EntityFactory {
    @Override
    public Transaction createTransaction() {
        return new TransactionEntity();
    }

    @Override
    public AnswerPrice createAnswerPrice() {
        return new PersistentAnswerPrice();
    }
}

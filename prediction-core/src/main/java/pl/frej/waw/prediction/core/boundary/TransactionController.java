package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.Question;
import pl.frej.waw.prediction.core.entity.Transaction;
import pl.frej.waw.prediction.core.entity.User;

import java.util.List;

public interface TransactionController {
    List<Transaction> find(String userId);
    void buyBundle(User user, Question question, Long quantity);

    void make(Offer offer, Long userId);
}

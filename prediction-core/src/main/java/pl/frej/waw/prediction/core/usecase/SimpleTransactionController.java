package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.AnswerController;
import pl.frej.waw.prediction.core.boundary.TransactionController;
import pl.frej.waw.prediction.core.entity.*;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.frej.waw.prediction.core.persistence.Transactions;
import pl.frej.waw.prediction.core.persistence.Users;

import java.util.List;
import java.util.Optional;

public class SimpleTransactionController implements TransactionController {
    private final Transactions transactions;
    private final Answers answers;
    private final AnswerController answerController;
    private final Users users;

    private EntityFactory entityFactory;

    public SimpleTransactionController(Transactions transactions, Answers answers, AnswerController answerController, Users users, EntityFactory entityFactory) {
        this.transactions = transactions;
        this.answers = answers;
        this.answerController = answerController;
        this.users = users;
        this.entityFactory = entityFactory;
    }

    @Override public List<Transaction> find(String userId) {
        return transactions.find();
    }

    @Override public void make() {
        answers.findAll().stream().filter(this::hasValidTransactions).forEach(this::complete);
    }

    @Override
    public void buyBundle(User user, Question question, Long quantity) {
        if(question.getCompletionValue() * quantity <= user.getFunds()){
            for (Answer answer : question.getAnswers()) {
                user.addAnswer(answer, quantity);
            }
            user.setFunds(user.getFunds()-quantity*question.getCompletionValue());
        }
        User updated = users.update(user);
        if(updated.getFunds().compareTo(user.getFunds())==0){
            complete(question);
        }
    }

    private boolean hasValidTransactions(Answer answer) {
        Optional<Long> buyPrice = answerController.getBuyPrice(answer);
        Optional<Long> sellPrice = answerController.getSellPrice(answer);
        return buyPrice.isPresent() && sellPrice.isPresent() && buyPrice.get() >= sellPrice.get();
    }

    private void complete(Answer answer) {
        Transaction transaction = entityFactory.createTransaction();
        transactions.add(transaction);
    }

    private void complete(Question question) {
        Transaction transaction = entityFactory.createTransaction();
        transactions.add(transaction);
    }

}

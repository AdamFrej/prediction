package pl.waw.frej.prediction.core.operations;


import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.Transaction;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.core.boundary.collection.Transactions;
import pl.waw.frej.prediction.core.boundary.collection.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Bundle {
    private User operator;
    private Long price;
    private List<Answer> answers;
    private User makler;
    private Long quantity;

    private final Users users;
    private final Transactions transactions;

    public Bundle(Users users, Transactions transactions) {
        this.users = users;
        this.transactions = transactions;
    }

    public void buy(User user, Question question, Long quantity) {
        if(question.getCompletionTime().isAfter(LocalDateTime.now()))
            return;

        this.quantity=quantity;
        price = question.getCompletionValue() * quantity;
        answers = question.getAnswers();
        operator = question.getOperator();
        makler = user;

        if (price > makler.getFunds()) {
            return;
        }

        exchangeAnswersForFunds();
        updateUsers();
        updateTransactions();
    }

    private void updateTransactions() {
        for (Answer answer : answers) {
            Transaction t = transactions.create();
            t.setAuthor(makler);
            t.setPrice(price / answers.size());
            t.setQuantity(quantity);
            t.setAnswer(answer);
            t.setBuyer(makler);
            t.setSeller(operator);
            t.setCompletionDate(LocalDateTime.now());
            transactions.add(t);
        }
    }

    private void updateUsers() {
        List<User> u = new ArrayList<>();
        u.add(makler);
        u.add(operator);
        users.update(u);
    }

    private void exchangeAnswersForFunds() {
        for (Answer answer : answers) {
            makler.addAnswer(answer, quantity);
        }
        makler.setFunds(makler.getFunds() - price);
        operator.setFunds(operator.getFunds() + price);
    }

}

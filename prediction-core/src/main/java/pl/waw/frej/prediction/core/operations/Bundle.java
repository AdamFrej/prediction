package pl.waw.frej.prediction.core.operations;


import pl.waw.frej.prediction.core.boundary.collection.Answers;
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
    private Long cost;
    private List<Answer> answerList;
    private User makler;
    private Long quantity;

    private final Users users;
    private final Transactions transactions;
    private final Answers answers;

    public Bundle(Users users, Transactions transactions, Answers answers) {
        this.users = users;
        this.transactions = transactions;
        this.answers = answers;
    }

    public void buy(User user, Question question, Long quantity) {
        if(question.getLiquidationDate().isBefore(LocalDateTime.now()))
            return;

        this.quantity=quantity;
        cost = question.getLiquidationValue() * quantity;
        answerList = question.getAnswers();
        operator = question.getOperator();
        makler = user;

        if (cost > makler.getFunds()) {
            return;
        }

        exchangeAnswersForFunds();
        updateUsers();
        answers.update(answerList);
        updateTransactions();
    }

    private void updateTransactions() {
        for (Answer answer : answerList) {
            Transaction t = transactions.create();
            t.setAuthor(makler);
            t.setPrice(cost/ answerList.size() / quantity);
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
        for (Answer answer : answerList) {
//            answer.addOwner(makler);
            makler.addAnswer(answer, quantity);
        }
        makler.setFunds(makler.getFunds() - cost);
        operator.setFunds(operator.getFunds() + cost);
    }

}

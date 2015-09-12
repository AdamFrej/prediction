package pl.waw.frej.prediction.core.operations;

import pl.waw.frej.prediction.core.boundary.collection.Questions;
import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.core.boundary.collection.Answers;
import pl.waw.frej.prediction.core.boundary.collection.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Liquidation {

    private final Answers answers;
    private final Users users;
    private final Questions questions;
    private List<Answer> answersToUpdate;
    private List<User> usersToUpdate;

    public Liquidation(Answers answers, Users users, Questions questions) {
        this.answers = answers;
        this.users = users;
        this.questions = questions;
    }

    public void liquidate(Question question, Answer payingAnswer){
        if(question.getLiquidationDate().isAfter(LocalDateTime.now()) || question.isLiquidated())
            return;

        answersToUpdate = question.getAnswers();
        usersToUpdate = new ArrayList<>();

        for (Answer answer : answersToUpdate) {
            for (User owner : answer.getOwners()) {
                Long quantity = owner.getAnswerQuantities().get(answer);
                owner.removeAnswer(answer, quantity);
                if(answer.equals(payingAnswer)){
                    Long payment = quantity * question.getLiquidationValue();
                    User operator = question.getOperator();
                    owner.modifyFunds(payment);
                    operator.modifyFunds(-payment);
                    usersToUpdate.add(operator);
                }
                usersToUpdate.add(owner);
            }
            answer.setLiquidated(true);
        }
        answers.update(answersToUpdate);
        users.update(usersToUpdate);
        question.setLiquidated(true);
        questions.update(question);
    }
}

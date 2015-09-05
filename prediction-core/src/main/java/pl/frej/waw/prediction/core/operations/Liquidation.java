package pl.frej.waw.prediction.core.operations;

import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.entity.Question;
import pl.frej.waw.prediction.core.boundary.entity.User;
import pl.frej.waw.prediction.core.boundary.collection.Answers;
import pl.frej.waw.prediction.core.boundary.collection.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Liquidation {

    private final Answers answers;
    private final Users users;
    private List<Answer> answersToUpdate;
    private List<User> usersToUpdate;

    public Liquidation(Answers answers, Users users) {
        this.answers = answers;
        this.users = users;
    }

    public void liquidate(Question question, Answer payingAnswer){
        if(question.getCompletionTime().isBefore(LocalDateTime.now()))
            return;

        answersToUpdate = question.getAnswers();
        usersToUpdate = new ArrayList<>();

        for (Answer answer : answersToUpdate) {
            for (User owner : answer.getOwners()) {
                Long quantity = owner.getAnswerQuantities().get(answer);
                owner.removeAnswer(answer, quantity);
                if(answer.equals(payingAnswer)){
                    Long payment = quantity * question.getCompletionValue();
                    User operator = question.getOperator();
                    owner.modifyFunds(payment);
                    operator.modifyFunds(-payment);
                    usersToUpdate.add(operator);
                }
                usersToUpdate.add(owner);
            }
        }
        answers.update(answersToUpdate);
        users.update(usersToUpdate);
    }
}

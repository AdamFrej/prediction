package pl.frej.waw.prediction.core.usecase;


import pl.frej.waw.prediction.core.boundary.control.Operator;
import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.entity.Question;
import pl.frej.waw.prediction.core.operations.AnswerName;
import pl.frej.waw.prediction.core.operations.Liquidation;
import pl.frej.waw.prediction.core.boundary.persistence.Answers;
import pl.frej.waw.prediction.core.boundary.persistence.Questions;
import pl.frej.waw.prediction.core.boundary.persistence.Users;

import java.time.LocalDateTime;

public class OperatorImpl implements Operator {
    private final Questions questions;
    private final Answers answers;

    private final Liquidation liquidation;

    public OperatorImpl(Questions questions, Answers answers, Users users) {
        this.questions = questions;
        this.answers = answers;
        this.liquidation = new Liquidation(answers,users);
    }

    @Override
    public void add(Question question) {
        AnswerName name = new AnswerName(question.getName());

        for (Answer answer : question.getAnswers()) {
            answer.setName(name.next());
            answer.setCompletionTime(question.getCompletionTime());
            answers.add(answer);
        }
        questions.add(question);
    }

    @Override
    public void liquidate(Question question, Answer payingAnswer) {
        if (question.getCompletionTime().isBefore(LocalDateTime.now()))
            return;
        liquidation.liquidate(question, payingAnswer);
    }
}

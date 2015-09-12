package pl.waw.frej.prediction.core.usecase;


import pl.waw.frej.prediction.core.boundary.collection.Answers;
import pl.waw.frej.prediction.core.boundary.collection.Questions;
import pl.waw.frej.prediction.core.boundary.collection.Users;
import pl.waw.frej.prediction.core.boundary.control.Operator;
import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.core.operations.AnswerName;
import pl.waw.frej.prediction.core.operations.Liquidation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OperatorImpl implements Operator {
    private final Questions questions;
    private final Answers answers;

    private final Liquidation liquidation;

    public OperatorImpl(Questions questions, Answers answers, Users users) {
        this.questions = questions;
        this.answers = answers;
        this.liquidation = new Liquidation(answers, users, questions);
    }

    @Override
    public void add(Question question) {
        AnswerName name = new AnswerName(question.getName());

        for (Answer answer : question.getAnswers()) {
            answer.setName(name.next());
            answer.setLiquidationDate(question.getLiquidationDate());
        }
        questions.add(question);
    }

    @Override
    public void liquidate(Question question, Answer payingAnswer) {
        Optional<Question> q = questions.findOne(question.getId());
        Optional<Answer> a = answers.find(payingAnswer.getId());

        if (q.isPresent() && q.get().getLiquidationDate().isAfter(LocalDateTime.now()))
            return;
        if(a.isPresent())
            liquidation.liquidate(q.get(), a.get());
    }

    @Override
    public List<Question> findQuestions(User user) {
        List<Question> byOperator = questions.findByOperator(user);
        return byOperator.stream().filter(question -> !question.isLiquidated()).collect(Collectors.toList());
    }

    @Override
    public List<Question> findLiquidationQuestions(User user) {
        List<Question> byOperatorAndDateBefore = questions.findByOperatorAndDateBefore(user, LocalDateTime.now());
        return byOperatorAndDateBefore.stream().filter(question -> !question.isLiquidated()).collect(Collectors.toList());
    }
}

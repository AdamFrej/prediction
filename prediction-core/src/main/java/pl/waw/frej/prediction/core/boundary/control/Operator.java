package pl.waw.frej.prediction.core.boundary.control;

import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;

import java.util.List;

public interface Operator {
    void add(Question question);

    void liquidate(Question question, Answer payingAnswer);

    List<Question> findQuestions(User user);
    List<Question> findLiquidationQuestions(User user);
}

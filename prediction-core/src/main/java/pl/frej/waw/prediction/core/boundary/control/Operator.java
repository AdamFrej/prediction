package pl.frej.waw.prediction.core.boundary.control;

import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.entity.Question;

public interface Operator {
    void add(Question question);

    void liquidate(Question question, Answer payingAnswer);
}

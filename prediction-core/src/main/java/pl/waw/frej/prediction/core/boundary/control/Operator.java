package pl.waw.frej.prediction.core.boundary.control;

import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;

public interface Operator {
    void add(Question question);

    void liquidate(Question question, Answer payingAnswer);
}

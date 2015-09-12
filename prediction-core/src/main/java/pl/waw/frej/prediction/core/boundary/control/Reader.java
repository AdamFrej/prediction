package pl.waw.frej.prediction.core.boundary.control;

import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.Quote;

import java.util.List;
import java.util.Optional;

public interface Reader {
    List<Question> readAllQuestions();

    Optional<Question> readQuestion(Long id);
    Optional<Question> readQuestion(Answer answer);

    Optional<Answer> readAnswer(Long id);
}

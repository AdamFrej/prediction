package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Question;

import java.util.List;

public interface QuestionController {
    Question read(Long id);

    List<Question> read();
    void add(Question question);
}

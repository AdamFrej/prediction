package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Question;

import java.util.List;

public interface QuestionController {
    Question read(String id);

    List<Question> read();
}

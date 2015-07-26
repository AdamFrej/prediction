package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.QuestionController;
import pl.frej.waw.prediction.core.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class SimpleQuestionController implements QuestionController {
    @Override public Question read(String id) {
        return new Question();
    }

    @Override public List<Question> read() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        return questions;
    }
}

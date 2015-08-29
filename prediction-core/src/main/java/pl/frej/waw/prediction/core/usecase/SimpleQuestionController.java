package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.QuestionController;
import pl.frej.waw.prediction.core.entity.Question;
import pl.frej.waw.prediction.core.persistence.Questions;

import java.util.List;

public class SimpleQuestionController implements QuestionController {
    private final Questions questions;

    public SimpleQuestionController(Questions questions) {
        this.questions = questions;
    }

    @Override public Question read(Long id) {
        return questions.find(id);
    }

    @Override public List<Question> read() {
        return questions.find();
    }
}

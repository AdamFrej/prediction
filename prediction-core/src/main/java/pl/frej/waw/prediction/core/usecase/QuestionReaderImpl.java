package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.control.QuestionReader;
import pl.frej.waw.prediction.core.boundary.entity.Question;
import pl.frej.waw.prediction.core.boundary.collection.Questions;

import java.util.List;
import java.util.Optional;

public class QuestionReaderImpl implements QuestionReader {
    private final Questions questions;

    public QuestionReaderImpl(Questions questions) {
        this.questions = questions;
    }

    @Override
    public List<Question> read() {
        return questions.find();
    }

    @Override
    public Optional<Question> read(Long id) {
        return questions.findOne(id);
    }
}

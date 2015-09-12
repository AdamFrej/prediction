package pl.waw.frej.prediction.core.usecase;

import pl.waw.frej.prediction.core.boundary.collection.Answers;
import pl.waw.frej.prediction.core.boundary.collection.Offers;
import pl.waw.frej.prediction.core.boundary.control.Reader;
import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.collection.Questions;
import pl.waw.frej.prediction.core.boundary.entity.Quote;
import pl.waw.frej.prediction.core.operations.Market;

import java.util.List;
import java.util.Optional;

public class ReaderImpl implements Reader {
    private final Questions questions;
    private final Answers answers;

    public ReaderImpl(Questions questions, Answers answers, Offers offers) {
        this.questions = questions;
        this.answers = answers;
    }

    @Override
    public List<Question> readAllQuestions() {
        return questions.find();
    }

    @Override
    public Optional<Question> readQuestion(Long id) {
        return questions.findOne(id);
    }

    @Override
    public Optional<Question> readQuestion(Answer answer) {
        return questions.findByAnswer(answer);
    }

    @Override
    public Optional<Answer> readAnswer(Long id) {
        return answers.find(id);
    }
}

package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.AnswerController;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Answers;

import java.util.List;

public class SimpleAnswerController implements AnswerController {
    private final Answers answers;

    public SimpleAnswerController(Answers answers) {
        this.answers = answers;
    }

    @Override public List<Answer> find(User user) {
        return answers.findByUser(user);
    }
}

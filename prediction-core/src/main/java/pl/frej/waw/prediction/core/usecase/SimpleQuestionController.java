package pl.frej.waw.prediction.core.usecase;

import com.google.common.collect.Lists;
import pl.frej.waw.prediction.core.boundary.QuestionController;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Question;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.frej.waw.prediction.core.persistence.Questions;

import java.util.ArrayList;
import java.util.List;

public class SimpleQuestionController implements QuestionController {
    private final Questions questions;
    private final Answers answers;

    public SimpleQuestionController(Questions questions, Answers answers) {
        this.questions = questions;
        this.answers = answers;
    }

    @Override public Question read(Long id) {
        return questions.find(id);
    }

    @Override public List<Question> read() {
        return questions.find();
    }

    @Override
    public void add(Question question) {
        List<Answer> as = new ArrayList<>();

        for (int i = 0; i < question.getAnswers().size(); i++) {
            Answer a = question.getAnswers().get(i);
            a.setName(question.getName() + i);
            a.setCompletionTime(question.getCompletionTime());
            answers.add(a);
            as.add(a);
        }
        questions.add(question);
    }
}

package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.QuestionController;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Question;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.frej.waw.prediction.core.persistence.Questions;

import java.util.Iterator;
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
        List<Answer> as = question.getAnswers();
        for (int i = 0; i < as.size(); i++) {
            Answer a = as.get(i);
            a.setName(question.getName() + i);
            a.setCompletionTime(question.getCompletionTime());
            a.setQuestion(question);
            answers.add(a);
            as.add(a);
        }
        Question persisted = questions.add(question);
        for(Answer answer : persisted.getAnswers()){
            answer.setQuestion(persisted);
        }
    }
}

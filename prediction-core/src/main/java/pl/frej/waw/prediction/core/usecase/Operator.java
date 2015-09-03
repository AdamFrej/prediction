package pl.frej.waw.prediction.core.usecase;


import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Question;
import pl.frej.waw.prediction.core.operations.AnswerName;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.frej.waw.prediction.core.persistence.Questions;

public class Operator {
    private final Questions questions;
    private final Answers answers;

    public Operator(Questions questions, Answers answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public void add(Question question) {
        AnswerName name = new AnswerName(question.getName());

        for (Answer answer : question.getAnswers()) {
            answer.setName(name.next());
            answer.setCompletionTime(question.getCompletionTime());
            answers.add(answer);
        }
        questions.add(question);
    }
}

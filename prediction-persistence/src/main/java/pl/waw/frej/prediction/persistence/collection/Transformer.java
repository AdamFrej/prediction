package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Question;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import pl.waw.frej.prediction.persistence.database.entity.QuestionEntity;

@Service
public class Transformer {

    public AnswerEntity getAnswerEntity(Answer answer) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setName(answer.getName());
        answerEntity.setDescription(answer.getDescription());
        answerEntity.setCompletionTime(answer.getCompletionTime());
        return answerEntity;
    }

    public QuestionEntity getQuestionEntity(Question question) {
        QuestionEntity qE = new QuestionEntity();
        qE.setName(question.getName());
        qE.setDescription(question.getDescription());
        qE.setAnswers(Lists.newArrayList(
                Iterables.transform(question.getAnswers(), this::getAnswerEntity)));
        qE.setCompletionTime(question.getCompletionTime());
        return qE;
    }
}

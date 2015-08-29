package database;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import database.entity.AnswerEntity;
import database.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.persistence.Answers;

import java.util.List;

public class PersistentAnswers implements Answers {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public boolean add(Answer answer) {
        answerRepository.save((AnswerEntity) answer);
        return true;
    }

    @Override
    public boolean remove(Answer answer) {
        answerRepository.delete((AnswerEntity) answer);
        return false;
    }

    @Override
    public Answer find(Long id) {
        return answerRepository.findOne(id).orElse(null);
    }

    @Override
    public List<Answer> findByUser(String userId) {
        return findAll();
    }

    @Override
    public List<Answer> findAll() {
        return Lists.newArrayList(Iterables.transform(answerRepository.findAll(), answerEntity -> (Answer) answerEntity));
    }
}

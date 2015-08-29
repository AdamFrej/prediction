package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.entity.User;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import pl.waw.frej.prediction.persistence.database.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.persistence.Answers;

import java.util.List;

@Service
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
    public List<Answer> findByUser(User user) {
        return null;
        //return Lists.newArrayList(Iterables.transform(answerRepository.findByUser(user), answerEntity -> (Answer) answerEntity));
    }

    @Override
    public List<Answer> findAll() {
        return Lists.newArrayList(Iterables.transform(answerRepository.findAll(), answerEntity -> (Answer) answerEntity));
    }
}

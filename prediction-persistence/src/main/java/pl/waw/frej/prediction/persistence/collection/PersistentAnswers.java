package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.Question;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import pl.waw.frej.prediction.persistence.database.repository.AnswerRepository;

import java.util.List;

@Service
public class PersistentAnswers implements Answers {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private Transformer transformer;

    @Override
    public boolean add(Answer answer) {
        AnswerEntity answerEntity = transformer.getAnswerEntity(answer);
        answerRepository.save(answerEntity);
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

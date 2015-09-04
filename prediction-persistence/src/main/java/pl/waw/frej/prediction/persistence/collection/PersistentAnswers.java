package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.persistence.Answers;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import pl.waw.frej.prediction.persistence.database.repository.AnswerRepository;

import java.util.List;
import java.util.Optional;

@Component
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
    public Optional<Answer> find(Long id) {
        Optional<AnswerEntity> one = answerRepository.findOne(id);
        return Optional.ofNullable((Answer) one.orElse(null));
    }

    @Override
    public List<Answer> findAll() {
        return Lists.newArrayList(answerRepository.findAll());
    }

    @Override
    public Answer update(Answer answer) {
        return answerRepository.save(transformer.getAnswerEntity(answer));
    }

    @Override
    public List<Answer> update(List<Answer> answers) {
        return Lists.newArrayList(answerRepository.save(Iterables.transform(answers, transformer::getAnswerEntity)));
    }

}

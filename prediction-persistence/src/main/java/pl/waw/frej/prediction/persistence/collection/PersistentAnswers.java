package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.frej.prediction.core.boundary.collection.Answers;
import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import pl.waw.frej.prediction.persistence.database.repository.AnswerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Component
public class PersistentAnswers implements Answers {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public boolean add(Answer answer) {
        AnswerEntity answerEntity = (AnswerEntity)answer;
        answerRepository.saveAndFlush(answerEntity);
        return true;
    }

    @Override
    public boolean remove(Answer answer) {
        answerRepository.delete((AnswerEntity) answer);
        return false;
    }

    @Override
    public Optional<Answer> find(Long id) {
        Optional<Answer> ret = Optional.empty();

        Optional<AnswerEntity> answer = answerRepository.findOne(id);
        if(answer.isPresent() && !answer.get().isLiquidated())
            ret = Optional.of((Answer)answer.get());

        return ret;
    }

    @Override
    public List<Answer> findAll() {
        return new ArrayList<>(notLiquidated(answerRepository.findAll()));
    }

    @Override
    public Answer update(Answer answer) {
        return answerRepository.save((AnswerEntity) answer);
    }

    @Override
    public List<Answer> update(List<Answer> answers) {
        return Lists.newArrayList(answerRepository.save(Iterables.transform(answers, answer -> (AnswerEntity) answer)));
    }


    private List<AnswerEntity> notLiquidated(List<AnswerEntity> list) {
        return list.stream().filter(answerEntity -> !answerEntity.isLiquidated()).collect(Collectors.toList());
    }

}

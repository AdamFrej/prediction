package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.frej.prediction.core.boundary.collection.Questions;
import pl.waw.frej.prediction.core.boundary.entity.Answer;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.persistence.database.entity.QuestionEntity;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
import pl.waw.frej.prediction.persistence.database.repository.QuestionRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class PersistentQuestions implements Questions {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> find() {
        return Lists.newArrayList(Iterables.transform(questionRepository.findAll(), questionEntity -> (Question) questionEntity));
    }

    @Override
    public List<Question> findByOperator(User user) {
        return new ArrayList<>(questionRepository.findByOperator((UserEntity) user));
    }

    @Override
    public Optional<Question> findOne(Long id) {
        return Optional.ofNullable((Question) questionRepository.findOne(id).orElse(null));
    }

    @Override
    public QuestionEntity add(Question question) {
        return questionRepository.save((QuestionEntity) question);
    }

    @Override
    public List<Question> findByOperatorAndDateBefore(User user, LocalDateTime date) {
        return new ArrayList<>(questionRepository.findByOperatorAndLiquidationDateBefore((UserEntity) user, date));
    }

    @Override
    public void update(Question question) {
        questionRepository.save((QuestionEntity) question);
    }


}

package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.waw.frej.prediction.core.boundary.collection.Questions;
import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.persistence.database.entity.QuestionEntity;
import pl.waw.frej.prediction.persistence.database.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Component
public class PersistentQuestions implements Questions {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> find() {
        return Lists.newArrayList(Iterables.transform(questionRepository.findAll(), questionEntity -> (Question) questionEntity));
    }

    @Override
    public Optional<Question> findOne(Long id) {
        return Optional.ofNullable((Question) questionRepository.findOne(id).orElse(null));
    }

    @Override
    public QuestionEntity add(Question question) {

        return questionRepository.save((QuestionEntity) question);
    }


}

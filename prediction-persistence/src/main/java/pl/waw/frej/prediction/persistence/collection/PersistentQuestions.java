package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.entity.Question;
import pl.frej.waw.prediction.core.persistence.Questions;
import pl.waw.frej.prediction.persistence.database.repository.QuestionRepository;

import java.util.List;

@Service
public class PersistentQuestions implements Questions {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> find() {
        return Lists.newArrayList(Iterables.transform(questionRepository.findAll(), questionEntity -> (Question)questionEntity));
    }

    @Override
    public Question find(Long id) {
        return (Question)questionRepository.findOne(id).orElse(null);
    }
}
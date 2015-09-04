package pl.frej.waw.prediction.core.persistence;

import pl.frej.waw.prediction.core.entity.Question;

import java.util.List;
import java.util.Optional;

public interface Questions {
    List<Question> find();

    Optional<Question> find(Long id);

    Question add(Question question);
}

package pl.frej.waw.prediction.core.boundary.collection;

import pl.frej.waw.prediction.core.boundary.entity.Question;

import java.util.List;
import java.util.Optional;

public interface Questions {
    List<Question> find();

    Optional<Question> findOne(Long id);

    Question add(Question question);
}

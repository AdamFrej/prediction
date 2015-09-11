package pl.waw.frej.prediction.core.boundary.collection;

import pl.waw.frej.prediction.core.boundary.entity.Question;
import pl.waw.frej.prediction.core.boundary.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface Questions {
    List<Question> find();

    List<Question> findByOperator(User user);

    Optional<Question> findOne(Long id);

    Question add(Question question);

    List<Question> findByOperatorAndDateBefore(User user, LocalDateTime date);

    void update(Question question);
}

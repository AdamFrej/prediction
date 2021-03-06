package pl.waw.frej.prediction.core.boundary.collection;

import pl.waw.frej.prediction.core.boundary.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface Answers {
    boolean add(Answer answer);

    boolean remove(Answer answer);

    Optional<Answer> find(Long id);

    List<Answer> findAll();

    List<Answer> update(List<Answer> answers);

    Answer update(Answer answer);
}

package pl.frej.waw.prediction.core.persistence;

import pl.frej.waw.prediction.core.entity.Answer;

import java.util.List;

public interface Answers {
    boolean add(Answer answer);

    boolean remove(Answer answer);

    Answer find(String Id);

    List<Answer> findByUser(String userId);
}

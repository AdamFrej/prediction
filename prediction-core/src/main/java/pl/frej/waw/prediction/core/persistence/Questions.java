package pl.frej.waw.prediction.core.persistence;

import pl.frej.waw.prediction.core.entity.Question;

import java.util.List;

public interface Questions {
    List<Question> find();

    Question find(Long id);

    void add(Question question);
}

package pl.frej.waw.prediction.core.boundary.control;

import pl.frej.waw.prediction.core.boundary.entity.Question;

import java.util.List;
import java.util.Optional;

/**
 * Created by afrej on 2015-09-05.
 */
public interface QuestionReader {
    List<Question> read();

    Optional<Question> read(Long id);
}

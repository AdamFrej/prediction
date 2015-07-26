package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Answer;

import java.util.List;

public interface AnswerController {
    List<Answer> find(String userId);
}

package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.*;

import java.util.List;
import java.util.Optional;

public interface AnswerController {
    List<Answer> find(User user);

    Optional<Long> getSellPrice(Answer answer);

    Optional<Long> getBuyPrice(Answer answer);

    Optional<Long> getAveragePrice(Answer answer);

    List<AnswerPrice> getPrices();
}

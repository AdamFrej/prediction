package pl.frej.waw.prediction.core.boundary;

import pl.frej.waw.prediction.core.entity.Answer;
import pl.frej.waw.prediction.core.entity.AnswerPrice;
import pl.frej.waw.prediction.core.entity.OfferType;
import pl.frej.waw.prediction.core.entity.User;

import java.util.List;

public interface AnswerController {
    List<Answer> find(User user);

    Long getSellPrice(Answer answer);

    Long getBuyPrice(Answer answer);

    List<AnswerPrice> getPrices();
}

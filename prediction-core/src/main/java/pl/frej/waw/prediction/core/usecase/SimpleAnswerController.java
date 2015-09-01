package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.AnswerController;
import pl.frej.waw.prediction.core.entity.*;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.frej.waw.prediction.core.persistence.Offers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleAnswerController implements AnswerController {
    private final Answers answers;
    private final Offers offers;
    private final EntityFactory entityFactory;

    public SimpleAnswerController(Answers answers, Offers offers, EntityFactory entityFactory) {
        this.answers = answers;
        this.offers = offers;
        this.entityFactory = entityFactory;
    }

    @Override
    public List<Answer> find(User user) {
        return answers.findByUser(user);
    }

    @Override
    public Optional<Long> getSellPrice(Answer answer) {
        return getPrice(answer, OfferType.SELL);
    }

    @Override
    public Optional<Long> getBuyPrice(Answer answer) {
        return getPrice(answer, OfferType.BUY);
    }

    @Override
    public Optional<Long> getAveragePrice(Answer answer) {
        Optional<Long> buyPrice = getBuyPrice(answer);
        Optional<Long> sellPrice = getSellPrice(answer);
        if (buyPrice.isPresent() && sellPrice.isPresent())
            return Optional.of((buyPrice.get() + sellPrice.get()) / 2);
        if (buyPrice.isPresent())
            return buyPrice;
        if (sellPrice.isPresent())
            return sellPrice;
        return Optional.empty();
    }

    @Override
    public List<AnswerPrice> getPrices() {
        return answers.findAll().stream().map(this::getAnswerPrice).collect(Collectors.toList());
    }


    private Optional<Long> getPrice(Answer answer, OfferType type) {
        List<Offer> offers = this.offers.findByAnswerAndType(answer.getId(), type);
        return Optional.ofNullable(offers.isEmpty() ? null : offers.get(0).getPrice());
    }

    private AnswerPrice getAnswerPrice(Answer answer) {
        AnswerPrice a = entityFactory.createAnswerPrice();
        a.setAnswer(answer);
        a.setBuyPrice(getBuyPrice(answer).orElse(null));
        a.setSellPrice(getSellPrice(answer).orElse(null));
        a.setAveragePrice(getAveragePrice(answer).orElse(null));
        return a;
    }
}

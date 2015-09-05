package pl.frej.waw.prediction.core.operations;

import pl.frej.waw.prediction.core.boundary.entity.Answer;
import pl.frej.waw.prediction.core.boundary.entity.Quote;
import pl.frej.waw.prediction.core.boundary.entity.Offer;
import pl.frej.waw.prediction.core.boundary.entity.OfferType;
import pl.frej.waw.prediction.core.boundary.collection.Answers;
import pl.frej.waw.prediction.core.boundary.collection.Offers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Market {

    private final Answers answers;
    private final Offers offers;

    public Market(Answers answers, Offers offers) {
        this.answers = answers;
        this.offers = offers;
    }

    public List<Quote> findQuotes() {
        return answers.findAll().stream().map(this::createQuote).collect(Collectors.toList());
    }

    private Quote createQuote(Answer answer) {
        Quote q = new Quote();
        q.setAnswerName(answer.getName());
        q.setBuyPrice(getPrice(answer, OfferType.BUY).orElse(null));
        q.setSellPrice(getPrice(answer, OfferType.SELL).orElse(null));
        q.setAveragePrice(getAveragePrice(answer).orElse(null));
        return q;
    }

    private Optional<Long> getAveragePrice(Answer answer) {
        Optional<Long> buyPrice = getPrice(answer, OfferType.BUY);
        Optional<Long> sellPrice = getPrice(answer, OfferType.SELL);
        if (buyPrice.isPresent() && sellPrice.isPresent())
            return Optional.of((buyPrice.get() + sellPrice.get()) / 2);
        if (buyPrice.isPresent())
            return buyPrice;
        if (sellPrice.isPresent())
            return sellPrice;
        return Optional.empty();
    }

    private Optional<Long> getPrice(Answer answer, OfferType type) {
        List<Offer> offers = this.offers.findByAnswerAndType(answer.getId(), type);
        return Optional.ofNullable(offers.isEmpty() ? null : offers.get(0).getPrice());
    }
}

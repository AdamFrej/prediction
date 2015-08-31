package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.AnswerController;
import pl.frej.waw.prediction.core.entity.*;
import pl.frej.waw.prediction.core.persistence.Answers;
import pl.frej.waw.prediction.core.persistence.Offers;

import java.util.ArrayList;
import java.util.List;

public class SimpleAnswerController implements AnswerController {
    private final Answers answers;
    private final Offers offers;
    private final EntityFactory entityFactory;

    public SimpleAnswerController(Answers answers, Offers offers, EntityFactory entityFactory) {
        this.answers = answers;
        this.offers = offers;
        this.entityFactory = entityFactory;
    }

    @Override public List<Answer> find(User user) {
        return answers.findByUser(user);
    }

    @Override
    public Long getSellPrice(Answer answer) {
        return getPrice(answer, OfferType.SELL);
    }
    @Override
    public Long getBuyPrice(Answer answer) {
        return getPrice(answer, OfferType.BUY);
    }

    private Long getPrice(Answer answer, OfferType type) {
        List<Offer> offers = this.offers.findByAnswerAndType(answer.getId(), type);
        return offers == null && offers.isEmpty() ? 0 : offers.get(0).getPrice();
    }

    @Override
    public List<AnswerPrice> getPrices(){
        ArrayList<AnswerPrice> ap = new ArrayList<>();
        for(Answer answer : answers.findAll()){
            AnswerPrice a = entityFactory.createAnswerPrice();

            a.setBuyPrice(getSellPrice(answer));
            a.setSellPrice(getBuyPrice(answer));
            a.setAveragePrice((a.getBuyPrice()+a.getSellPrice())/2);

            ap.add(a);
        }
        return ap;
    }
}

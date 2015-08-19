package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.OfferController;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;
import pl.frej.waw.prediction.core.persistence.Offers;

import java.util.List;

public class SimpleOfferController implements OfferController {

    private Offers offers;

    public SimpleOfferController(Offers offers) {
        this.offers = offers;
    }

    @Override public boolean add(String questionId, String answerId, OfferType type, Integer price,
        Integer quantity) {
        offers.add(new Offer(type, price, quantity, answerId));
        return true;
    }

    @Override public List<Offer> find(String userId) {
        return offers.findByUser(userId);
    }

    @Override public boolean cancel(String OfferId) {
        offers.remove(OfferId);
        return true;
    }
}

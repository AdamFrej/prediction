package database;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import database.entity.OfferEntity;
import database.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.persistence.Offers;

import java.util.List;
import java.util.Optional;


public class PersistentOffers implements Offers {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public boolean add(Offer offer) {
        offerRepository.save((OfferEntity) offer);
        return true;
    }

    @Override
    public boolean remove(Long id) {
        Optional<OfferEntity> one = offerRepository.findOne(id);
        if(one.isPresent()) {
            offerRepository.delete(one.get());
            return true;
        }
        return false;
    }

    @Override
    public Offer find(Long id) {
        return offerRepository.findOne(id).orElse(null);
    }

    @Override
    public List<Offer> findByUser(Long userId) {
        return Lists.newArrayList(Iterables.transform(offerRepository.findAll(),offerEntity -> (Offer)offerEntity));
    }

    @Override
    public List<Offer> findByAnswer(Long answerId) {
        return Lists.newArrayList(Iterables.transform(offerRepository.findByAnswerId(answerId),offerEntity -> (Offer)offerEntity));
    }
}

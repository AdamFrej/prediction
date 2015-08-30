package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.persistence.Offers;
import pl.waw.frej.prediction.persistence.database.entity.OfferEntity;
import pl.waw.frej.prediction.persistence.database.repository.OfferRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersistentOffers implements Offers {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public boolean add(Offer offer) {
        OfferEntity oE = new OfferEntity();
        oE.setAnswerId(offer.getAnswerId());
        oE.setOfferType(offer.getOfferType());
        oE.setPrice(offer.getPrice());
        oE.setQuantity(offer.getQuantity());
        offerRepository.save(oE);
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
        return null;
//        return Lists.newArrayList(Iterables.transform(offerRepository.findByAnswerId(answerId),offerEntity -> (Offer)offerEntity));
    }
}

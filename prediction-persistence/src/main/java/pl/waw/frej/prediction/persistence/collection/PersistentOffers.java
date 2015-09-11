package pl.waw.frej.prediction.persistence.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.frej.prediction.core.boundary.collection.Offers;
import pl.waw.frej.prediction.core.boundary.entity.Offer;
import pl.waw.frej.prediction.core.boundary.entity.OfferType;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.persistence.database.entity.OfferEntity;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
import pl.waw.frej.prediction.persistence.database.repository.OfferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
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
        if (one.isPresent()) {
            offerRepository.delete(one.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Offer> find(Long id) {
        return Optional.ofNullable((Offer) offerRepository.findOne(id).orElse(null));
    }

    @Override
    public List<Offer> findByUser(User user) {

        return transformOffers(offerRepository.findByUser((UserEntity) user));
    }

    @Override
    public List<Offer> findByAnswer(Long answerId) {
        return transformOffers(offerRepository.findByAnswerId(answerId));
    }

    @Override
    public List<Offer> findByAnswerAndType(Long answerId, OfferType offerType) {
        return OfferType.BUY.equals(offerType)
                ? transformOffers(offerRepository.findByAnswerIdAndOfferTypeOrderByPriceDesc(answerId, offerType))
                : transformOffers(offerRepository.findByAnswerIdAndOfferTypeOrderByPriceAsc(answerId, offerType));
    }

    @Override
    public Offer update(Offer offer) {
        Optional<OfferEntity> optional = offerRepository.findOne(offer.getId());
        if (optional.isPresent()) {
            OfferEntity oldOffer = optional.get();
            OfferEntity newOffer = (OfferEntity) offer;
            offerRepository.delete(oldOffer);
            OfferEntity save = offerRepository.save(newOffer);
            return save;
        } else
            return offer;
    }

    private List<Offer> transformOffers(List<OfferEntity> offerEntities) {
        return new ArrayList<>(offerEntities);
    }
}

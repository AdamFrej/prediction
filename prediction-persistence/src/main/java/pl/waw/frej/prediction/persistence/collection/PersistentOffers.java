package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.entity.Offer;
import pl.frej.waw.prediction.core.entity.OfferType;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Offers;
import pl.waw.frej.prediction.persistence.database.entity.OfferEntity;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
import pl.waw.frej.prediction.persistence.database.repository.OfferRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersistentOffers implements Offers {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private Transformer transformer;

    @Override
    public boolean add(Offer offer) {
        offerRepository.save(transformer.getOfferEntity(offer));
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
    public Offer find(Long id) {
        return offerRepository.findOne(id).orElse(null);
    }

    @Override
    public List<Offer> findByUser(User user) {
        return transformOffers(offerRepository.findByUser(transformer.getUserEntity(user)));
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
            OfferEntity newOffer = transformer.getOfferEntity(offer);
            offerRepository.delete(oldOffer);
            OfferEntity save = offerRepository.save(newOffer);
            return save;
        } else
            return offer;
    }

    private List<Offer> transformOffers(List<OfferEntity> offerEntities) {
        return Lists.newArrayList(offerEntities);
    }
}

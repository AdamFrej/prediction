package pl.waw.frej.prediction.persistence.database.repository;


import org.springframework.data.repository.Repository;
import pl.frej.waw.prediction.core.entity.OfferType;
import pl.waw.frej.prediction.persistence.database.entity.OfferEntity;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends Repository<OfferEntity,Long>{
    void delete(OfferEntity deleted);

    List<OfferEntity> findAll();
    List<OfferEntity> findByAnswerIdAndOfferTypeOrderByPriceDesc(Long answerId, OfferType offerType);
    List<OfferEntity> findByAnswerIdAndOfferTypeOrderByPriceAsc(Long answerId, OfferType offerType);
    List<OfferEntity> findByAnswerId(Long answerId);

    List<OfferEntity> findByUser(UserEntity userEntity);

    Optional<OfferEntity> findOne(Long id);

    OfferEntity save(OfferEntity persisted);
}

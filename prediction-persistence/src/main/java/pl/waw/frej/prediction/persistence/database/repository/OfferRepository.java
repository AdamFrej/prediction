package pl.waw.frej.prediction.persistence.database.repository;


import pl.waw.frej.prediction.persistence.database.entity.OfferEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends Repository<OfferEntity,Long>{
    void delete(OfferEntity deleted);

    List<OfferEntity> findAll();
//    List<OfferEntity> findByAnswerId(Long answerId);

    Optional<OfferEntity> findOne(Long id);

    OfferEntity save(OfferEntity persisted);
}

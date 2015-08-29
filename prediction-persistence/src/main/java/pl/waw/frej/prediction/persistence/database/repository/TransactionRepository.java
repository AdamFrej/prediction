package pl.waw.frej.prediction.persistence.database.repository;


import org.springframework.data.repository.Repository;
import pl.waw.frej.prediction.persistence.database.entity.TransactionEntity;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends Repository<TransactionEntity, Long> {
    void delete(TransactionEntity deleted);

    List<TransactionEntity> findAll();
//    List<TransactionEntity> findByAnswerId(Long answerId);

    Optional<TransactionEntity> findOne(Long id);

    TransactionEntity save(TransactionEntity persisted);
}

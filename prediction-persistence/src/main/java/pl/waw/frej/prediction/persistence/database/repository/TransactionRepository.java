package pl.waw.frej.prediction.persistence.database.repository;


import org.springframework.data.repository.Repository;
import pl.waw.frej.prediction.core.boundary.entity.Transaction;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import pl.waw.frej.prediction.persistence.database.entity.TransactionEntity;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends Repository<TransactionEntity, Long> {
    void delete(TransactionEntity deleted);

    List<TransactionEntity> findAll();
    List<TransactionEntity> findByAnswerOrderByCompletionDateDesc(AnswerEntity answerEntity);

    Optional<TransactionEntity> findOne(Long id);

    TransactionEntity save(TransactionEntity persisted);
    TransactionEntity saveAndFlush(TransactionEntity persisted);

    List<TransactionEntity> findByBuyer(UserEntity user);

    List<TransactionEntity> findBySeller(UserEntity user);
}

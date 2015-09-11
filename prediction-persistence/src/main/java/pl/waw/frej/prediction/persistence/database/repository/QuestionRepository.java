package pl.waw.frej.prediction.persistence.database.repository;


import org.springframework.data.repository.Repository;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.persistence.database.entity.QuestionEntity;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends Repository<QuestionEntity,Long>{
    void delete(QuestionEntity deleted);

    List<QuestionEntity> findAll();
    List<QuestionEntity> findByOperator(UserEntity operator);
//    List<QuestionEntity> findByAnswerId(Long answerId);

    Optional<QuestionEntity> findOne(Long id);

    QuestionEntity save(QuestionEntity persisted);

    List<QuestionEntity> findByOperatorAndLiquidationDateBefore(UserEntity user, LocalDateTime liquidationDate);
}

package pl.waw.frej.prediction.persistence.database.repository;


import org.springframework.data.repository.Repository;
import pl.waw.frej.prediction.persistence.database.entity.QuestionEntity;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends Repository<QuestionEntity,Long>{
    void delete(QuestionEntity deleted);

    List<QuestionEntity> findAll();
//    List<QuestionEntity> findByAnswerId(Long answerId);

    Optional<QuestionEntity> findOne(Long id);

    QuestionEntity save(QuestionEntity persisted);
}

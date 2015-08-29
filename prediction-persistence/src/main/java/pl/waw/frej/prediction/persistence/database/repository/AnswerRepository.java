package pl.waw.frej.prediction.persistence.database.repository;

import pl.frej.waw.prediction.core.entity.User;
import pl.waw.frej.prediction.persistence.database.entity.AnswerEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends Repository<AnswerEntity, Long> {
    void delete(AnswerEntity deleted);

    List<AnswerEntity> findAll();
//    List<AnswerEntity> findByUser(User user);

    Optional<AnswerEntity> findOne(Long id);

    AnswerEntity save(AnswerEntity persisted);
}

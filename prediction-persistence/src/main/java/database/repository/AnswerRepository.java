package database.repository;

import database.entity.AnswerEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends Repository<AnswerEntity, Long> {
    void delete(AnswerEntity deleted);

    List<AnswerEntity> findAll();

    Optional<AnswerEntity> findOne(Long id);

    AnswerEntity save(AnswerEntity persisted);
}

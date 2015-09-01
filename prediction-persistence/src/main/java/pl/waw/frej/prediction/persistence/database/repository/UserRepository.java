package pl.waw.frej.prediction.persistence.database.repository;

import org.springframework.data.repository.Repository;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<UserEntity,Long> {
    Optional<UserEntity> findOne(Long id);

    UserEntity save(UserEntity persisted);
    void delete(UserEntity userEntity);

    List<UserEntity> findAll();

}

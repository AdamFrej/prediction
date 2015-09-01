package pl.waw.frej.prediction.persistence.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Users;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
import pl.waw.frej.prediction.persistence.database.repository.UserRepository;

import java.util.Optional;

@Service
public class PersistentUsers implements Users {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Transformer transformer;

    @Override
    public User find(Long id) {
        return userRepository.findOne(id).orElse(null);
    }

    @Override
    @Transactional
    public User update(User user) {
        Optional<UserEntity> optional = userRepository.findOne(user.getId());
        if (optional.isPresent()) {
            UserEntity oldUser = optional.get();
            UserEntity newUser = transformer.getUserEntity(user);
            userRepository.delete(oldUser);
            UserEntity save = userRepository.save(newUser);
            return save;
        } else
            return user;
    }
}

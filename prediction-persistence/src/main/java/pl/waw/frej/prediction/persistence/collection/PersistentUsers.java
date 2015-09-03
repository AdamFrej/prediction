package pl.waw.frej.prediction.persistence.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Users;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
import pl.waw.frej.prediction.persistence.database.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersistentUsers implements Users {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Transformer transformer;

    @Override
    public Optional<User> find(Long id) {
        return Optional.ofNullable(userRepository.findOne(id).orElse(null));
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

    @Override
    @Transactional
    public void update(List<User> users){
        users.forEach(this::update);
    }

    @Override
    public void add(User user){
        userRepository.save(transformer.getUserEntity(user));
    }
}

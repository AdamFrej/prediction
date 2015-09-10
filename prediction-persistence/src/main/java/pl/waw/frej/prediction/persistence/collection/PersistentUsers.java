package pl.waw.frej.prediction.persistence.collection;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.frej.prediction.core.boundary.collection.Users;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;
import pl.waw.frej.prediction.persistence.database.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class PersistentUsers implements Users {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> find(Long id) {
        return Optional.ofNullable(userRepository.findOne(id).orElse(null));
    }

    @Override
    @Transactional
    public User update(User user) {

        return userRepository.save((UserEntity) user);
    }

    @Override
    public List<User> update(List<User> users){
        return Lists.newArrayList(userRepository.save(Iterables.transform(users, u->(UserEntity)u)));
    }

    @Override
    public void add(User user){

        userRepository.save((UserEntity) user);
    }
}

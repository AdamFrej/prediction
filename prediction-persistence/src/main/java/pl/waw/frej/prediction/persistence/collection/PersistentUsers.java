package pl.waw.frej.prediction.persistence.collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Users;
import pl.waw.frej.prediction.persistence.database.repository.UserRepository;

@Service
public class PersistentUsers implements Users {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User find(Long id) {
        return userRepository.findOne(id).orElse(null);
    }
}

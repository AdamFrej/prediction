package pl.waw.frej.prediction.core.boundary.control;

import pl.waw.frej.prediction.core.boundary.entity.User;

import java.util.List;
import java.util.Optional;


public interface Admin {
    void addUser(User user);

    void removeUser(Long userId);

    List<User> readUsers();
    Optional<User> findUser(Long userId);
}

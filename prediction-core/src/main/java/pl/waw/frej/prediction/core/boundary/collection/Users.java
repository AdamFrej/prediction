package pl.waw.frej.prediction.core.boundary.collection;

import pl.waw.frej.prediction.core.boundary.entity.User;

import java.util.List;
import java.util.Optional;

public interface Users {
    Optional<User> find(Long id);
    Optional<User> find(User user);
    Optional<User> find(String userName);

    User update(User user);

    List<User> update(List<User> users);

    void add(User user);

    void removeUser(User user);

    List<User> findAll();
}

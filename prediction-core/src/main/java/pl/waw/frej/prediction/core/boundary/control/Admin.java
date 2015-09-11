package pl.waw.frej.prediction.core.boundary.control;

import pl.waw.frej.prediction.core.boundary.entity.User;

import java.util.List;


public interface Admin {
    void addUser(User user);

    void removeUser(Long userId);

    List<User> readUsers();
}

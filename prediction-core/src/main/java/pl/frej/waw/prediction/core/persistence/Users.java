package pl.frej.waw.prediction.core.persistence;

import pl.frej.waw.prediction.core.entity.User;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

public interface Users {
    Optional<User> find(Long id);

    User update(User user);

    void update(List<User> users);

    void add(User user);
}

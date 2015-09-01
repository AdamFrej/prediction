package pl.frej.waw.prediction.core.persistence;

import pl.frej.waw.prediction.core.entity.User;

public interface Users {
    User find(Long id);

    User update(User user);
}

package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.entity.User;
import pl.frej.waw.prediction.core.persistence.Users;

public class Admin {

    private final Users users;

    public Admin(Users users) {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
    }
}

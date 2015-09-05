package pl.frej.waw.prediction.core.usecase;

import pl.frej.waw.prediction.core.boundary.control.Admin;
import pl.frej.waw.prediction.core.boundary.entity.User;
import pl.frej.waw.prediction.core.boundary.collection.Users;

public class AdminImpl implements Admin {

    private final Users users;

    public AdminImpl(Users users) {
        this.users = users;
    }

    @Override
    public void addUser(User user){
        users.add(user);
    }
}

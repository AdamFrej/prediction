package pl.waw.frej.prediction.core.usecase;

import pl.waw.frej.prediction.core.boundary.control.Admin;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.core.boundary.collection.Users;

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

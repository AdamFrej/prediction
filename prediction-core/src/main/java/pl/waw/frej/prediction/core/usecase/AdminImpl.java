package pl.waw.frej.prediction.core.usecase;

import pl.waw.frej.prediction.core.boundary.control.Admin;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.core.boundary.collection.Users;

import java.util.List;
import java.util.Optional;

public class AdminImpl implements Admin {

    private final Users users;

    public AdminImpl(Users users) {
        this.users = users;
    }

    @Override
    public void addUser(User user){
        users.add(user);
    }

    @Override
    public void removeUser(Long userId) {
        Optional<User> u = users.find(userId);
        if(u.isPresent())
            users.removeUser(u.get());
    }

    @Override
    public List<User> readUsers() {
        return users.findAll();
    }

    @Override
    public Optional<User> findUser(Long userId) {
        return users.find(userId);
    }
}

package pl.waw.frej.prediction.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.waw.frej.prediction.core.boundary.control.Admin;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.persistence.EntityFactory;

import javax.servlet.http.HttpSession;

@Service
public class UserProvider {

    @Autowired
    private Admin admin;

    public User from(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = EntityFactory.createUser();
            user.setFunds(15L);
            admin.addUser(user);
            session.setAttribute("user", user);
        }
        return user;
    }
}

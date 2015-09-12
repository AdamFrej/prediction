package pl.waw.frej.prediction.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.waw.frej.prediction.core.boundary.control.Admin;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.persistence.EntityFactory;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserProvider {

    public static final String MAKLER = "makler";
    @Autowired
    private Admin admin;
    public static final String OPERATOR = "operator";

    public User maklerFrom(HttpSession session) {
        User user = (User) session.getAttribute(MAKLER);
        if (user == null) {
            Optional<User> autoUser = admin.findUser(5L);
            if (autoUser.isPresent())
                user=autoUser.get();
            else {
                user = EntityFactory.createUser();
                user.setName(MAKLER);
                user.setFunds(15L);
                admin.addUser(user);
            }
            session.setAttribute(MAKLER, user);
        }
        return user;
    }

    public User operatorFrom(HttpSession session) {
        User user = (User) session.getAttribute(OPERATOR);
        if (user == null) {
            Optional<User> autoUser = admin.findUser(1L);
            if (autoUser.isPresent())
                user=autoUser.get();
            else {
                user = EntityFactory.createUser();
                user.setName(OPERATOR);
                user.setFunds(0L);
                admin.addUser(user);
            }
            session.setAttribute(OPERATOR, user);
        }
        return user;
    }
}

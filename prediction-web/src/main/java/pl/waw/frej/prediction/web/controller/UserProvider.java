package pl.waw.frej.prediction.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.frej.waw.prediction.core.usecase.Admin;
import pl.waw.frej.prediction.persistence.database.entity.UserEntity;

import javax.servlet.http.HttpSession;

@Service
public class UserProvider {

    @Autowired
    private Admin admin;

    public UserEntity from(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            user = new UserEntity();
            user.setFunds(15L);
            admin.addUser(user);
            session.setAttribute("user", user);
        }
        return user;
    }
}

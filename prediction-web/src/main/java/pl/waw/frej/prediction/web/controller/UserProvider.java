package pl.waw.frej.prediction.web.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.waw.frej.prediction.core.boundary.entity.User;
import pl.waw.frej.prediction.web.config.UserInfo;

import java.security.Principal;

@Service
public class UserProvider {

    public User from(Principal principal){
        return ((UserInfo)((UsernamePasswordAuthenticationToken)principal).getPrincipal()).getUser();
    }
}

package pl.waw.frej.prediction.web.config;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.waw.frej.prediction.core.boundary.entity.User;

import java.util.Collection;

public class UserInfo implements UserDetails, CredentialsContainer {
    private String password;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    private final User user;

    public UserInfo(String username, String password, Collection<? extends GrantedAuthority> authorities, User user) {
        this.password = password;
        this.username = username;
        this.authorities = authorities;
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void eraseCredentials() {
        password = null;
    }
}

package com.purchasing.support.user;

import com.purchasing.entity.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * @author vanessa
 */
@SessionScoped
public class UserSession implements Serializable {

    private User user;

    public boolean isLogged() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

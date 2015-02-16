package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.User;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class UserDAO extends DAOImpl<User,Long> {

    @Inject
    public UserDAO(Session session) {
        super(session);
    }


    public User findByUsernamePassword(String username, String password) {
        return null;
    }

    public Integer totalPagination(String search) {
        return null;
    }

    public List<User> pagination(String search, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    public User findByUsername(String username) {
        return null;
    }

    public User findByEmail(String email) {
        return null;
    }
}

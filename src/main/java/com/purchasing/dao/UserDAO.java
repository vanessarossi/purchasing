package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.User;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class UserDAO extends DAOImpl<User,Long> {

    @Inject
    public UserDAO(Session session) {
        super(session);
    }
}

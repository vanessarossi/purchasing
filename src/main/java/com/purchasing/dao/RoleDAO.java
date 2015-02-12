package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Role;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class RoleDAO extends DAOImpl<Role,Long> {

    @Inject
    public RoleDAO(Session session) {
        super(session);
    }
}

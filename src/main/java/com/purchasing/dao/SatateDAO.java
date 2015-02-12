package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.State;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class SatateDAO extends DAOImpl<State,Long> {

    @Inject
    public SatateDAO(Session session) {
        super(session);
    }
}

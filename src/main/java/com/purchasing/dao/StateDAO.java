package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.State;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class StateDAO extends DAOImpl<State,Long> {

    @Inject
    public StateDAO(Session session) {
        super(session);
    }
}

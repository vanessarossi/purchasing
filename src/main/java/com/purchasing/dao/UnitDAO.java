package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Unit;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class UnitDAO extends DAOImpl<Unit,Long> {

    @Inject
    public UnitDAO(Session session) {
        super(session);
    }
}

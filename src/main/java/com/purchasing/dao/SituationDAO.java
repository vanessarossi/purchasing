package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Situation;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class SituationDAO extends DAOImpl<Situation,Long> {

    @Inject
    public SituationDAO(Session session) {
        super(session);
    }
}

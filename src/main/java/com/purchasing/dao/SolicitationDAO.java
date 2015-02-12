package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Solicitation;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class SolicitationDAO extends DAOImpl<Solicitation,Long> {

    @Inject
    public SolicitationDAO(Session session) {
        super(session);
    }
}

package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Reception;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class ReceptionDAO extends DAOImpl<Reception,Long> {

    @Inject
    public ReceptionDAO(Session session) {
        super(session);
    }
}

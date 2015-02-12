package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.NaturalPerson;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class NaturalPersonDAO extends DAOImpl<NaturalPerson,Long> {

    @Inject
    public NaturalPersonDAO(Session session) {
        super(session);
    }
}

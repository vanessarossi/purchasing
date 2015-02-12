package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.SolicitationRequest;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class SolicitationRequestDAO extends DAOImpl<SolicitationRequest,Long> {

    @Inject
    public SolicitationRequestDAO(Session session) {
        super(session);
    }
}

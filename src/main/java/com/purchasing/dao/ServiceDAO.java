package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Service;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class ServiceDAO extends DAOImpl<Service,Long> {

    @Inject
    public ServiceDAO(Session session) {
        super(session);
    }
}

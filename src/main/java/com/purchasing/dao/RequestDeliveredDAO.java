package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.RequestDelivered;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class RequestDeliveredDAO extends DAOImpl<RequestDelivered,Long> {

    @Inject
    public RequestDeliveredDAO(Session session) {
        super(session);
    }
}

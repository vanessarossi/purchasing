package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.OrderRequest;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class OrderRequestDAO extends DAOImpl<OrderRequest,Long> {

    @Inject
    public OrderRequestDAO(Session session) {
        super(session);
    }
}

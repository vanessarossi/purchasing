package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.PurchaseOrder;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class PurchaseOrderDAO extends DAOImpl<PurchaseOrder,Long> {

    @Inject
    public PurchaseOrderDAO(Session session) {
        super(session);
    }
}

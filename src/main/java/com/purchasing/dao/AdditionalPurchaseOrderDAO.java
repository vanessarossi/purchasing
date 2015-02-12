package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.AdditionalPurchaseOrder;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class AdditionalPurchaseOrderDAO extends DAOImpl<AdditionalPurchaseOrder,Long>{

    @Inject
    public AdditionalPurchaseOrderDAO(Session session) {
        super(session);
    }
}

package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Supplier;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class SupplierDAO extends DAOImpl<Supplier,Long> {

    @Inject
    public SupplierDAO(Session session) {
        super(session);
    }
}

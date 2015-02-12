package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.CostCenter;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class CostCenterDAO extends DAOImpl<CostCenter,Long> {

    @Inject
    public CostCenterDAO(Session session) {
        super(session);
    }
}

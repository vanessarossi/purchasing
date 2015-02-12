package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.BudgetQuotation;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class BudgetQuotationDAO extends DAOImpl<BudgetQuotation,Long> {

    @Inject
    public BudgetQuotationDAO(Session session) {
        super(session);
    }
}

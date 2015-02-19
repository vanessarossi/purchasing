package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Budget;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class BudgetDAO extends DAOImpl<Budget,Long> {

    @Inject
    public BudgetDAO(Session session) {
        super(session);
    }

}

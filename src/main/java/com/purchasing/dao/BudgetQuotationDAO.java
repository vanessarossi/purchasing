package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Budget;
import com.purchasing.entity.BudgetQuotation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
public class BudgetQuotationDAO extends DAOImpl<BudgetQuotation,Long> {

    @Inject
    public BudgetQuotationDAO(Session session) {
        super(session);
    }

    public List<BudgetQuotation> findByBudgetId(Budget budget) {
        Criteria criteria = getSession().createCriteria(BudgetQuotation.class);
        criteria.add(Restrictions.eq("budget", budget));
        List<BudgetQuotation> budgetQuotations = criteria.list();
        return budgetQuotations;
    }
}

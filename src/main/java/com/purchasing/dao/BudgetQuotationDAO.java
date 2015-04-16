package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Budget;
import com.purchasing.entity.BudgetQuotation;
import com.purchasing.entity.Product;
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

    public List<BudgetQuotation> findByBudgetAndProduct(Budget budget, Product product) {
        Criteria criteria = getSession().createCriteria(BudgetQuotation.class);
        criteria.createAlias("quotationRequest","quotationRequest");
        criteria.createAlias("quotationRequest.solicitationRequest","solicitationRequest");
        criteria.add(Restrictions.eq("solicitationRequest.product", product));
        criteria.add(Restrictions.eq("budget", budget));
        List<BudgetQuotation> budgetQuotations = criteria.list();
        return budgetQuotations;
    }
}

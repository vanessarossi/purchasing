package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Budget;
import com.purchasing.entity.Quotation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class BudgetDAO extends DAOImpl<Budget,Long> {

    @Inject
    public BudgetDAO(Session session) {
        super(session);
    }

    public List<Budget> findByQuotationOrderPaymentInformation(Quotation quotation){
        Criteria criteria = getSession().createCriteria(Budget.class);
        criteria.createAlias("paymentInformationBudgets","paymentInformationBudget");
        criteria.createAlias("paymentInformationBudgets.paymentInformation", "paymentInformation");
        criteria.add(Restrictions.eq("quotation", quotation));
        criteria.addOrder(Order.asc("paymentInformation.totalPrice"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Budget> budgets = new ArrayList<>();
        budgets.addAll(criteria.list());
        return budgets;
    }

}

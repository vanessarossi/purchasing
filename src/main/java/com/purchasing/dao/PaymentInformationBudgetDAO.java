package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.PaymentInformationBudget;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class PaymentInformationBudgetDAO extends DAOImpl<PaymentInformationBudget,Long> {

    @Inject
    public PaymentInformationBudgetDAO(Session session) {
        super(session);
    }
}

package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.FormPayment;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class FormPaymentDAO extends DAOImpl<FormPayment,Long> {

    @Inject
    public FormPaymentDAO(Session session) {
        super(session);
    }
}

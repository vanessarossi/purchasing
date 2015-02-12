package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.PaymentInformation;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class PaymentInformationDAO extends DAOImpl<PaymentInformation,Long> {

    @Inject
    public PaymentInformationDAO(Session session) {
        super(session);
    }
}

package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.QuotationRequest;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class QuotationRequestDAO extends DAOImpl<QuotationRequest,Long> {

    @Inject
    public QuotationRequestDAO(Session session) {
        super(session);
    }
}

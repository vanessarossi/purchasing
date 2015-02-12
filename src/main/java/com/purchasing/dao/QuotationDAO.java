package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Quotation;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class QuotationDAO extends DAOImpl<Quotation,Long> {

    @Inject
    public QuotationDAO(Session session) {
        super(session);
    }
}

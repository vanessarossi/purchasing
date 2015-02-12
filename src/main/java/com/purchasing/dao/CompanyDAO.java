package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Company;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class CompanyDAO extends DAOImpl<Company,Long> {

    @Inject
    public CompanyDAO(Session session) {
        super(session);
    }
}

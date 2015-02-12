package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Approval;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class AddressDAO extends DAOImpl<Approval,Long> {

    @Inject
    public AddressDAO(Session session) {
        super(session);
    }
}

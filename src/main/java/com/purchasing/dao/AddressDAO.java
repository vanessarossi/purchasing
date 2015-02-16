package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Address;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class AddressDAO extends DAOImpl<Address,Long> {

    @Inject
    public AddressDAO(Session session) {
        super(session);
    }
}

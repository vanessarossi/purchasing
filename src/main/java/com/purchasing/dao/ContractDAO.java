package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Contract;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class ContractDAO extends DAOImpl<Contract,Long> {

    @Inject
    public ContractDAO(Session session) {
        super(session);
    }
}

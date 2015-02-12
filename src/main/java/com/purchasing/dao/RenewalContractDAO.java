package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.RenewalContract;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class RenewalContractDAO extends DAOImpl<RenewalContract,Long> {

    @Inject
    public RenewalContractDAO(Session session) {
        super(session);
    }
}

package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Approval;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class ApprovalDAO extends DAOImpl<Approval,Long> {

    @Inject
    public ApprovalDAO(Session session) {
        super(session);
    }
}

package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.TypeService;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class TypeServiceDAO extends DAOImpl<TypeService,Long> {

    @Inject
    public TypeServiceDAO(Session session) {
        super(session);
    }
}

package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Person;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class PersonDAO extends DAOImpl<Person,Long> {

    @Inject
    public PersonDAO(Session session) {
        super(session);
    }
}

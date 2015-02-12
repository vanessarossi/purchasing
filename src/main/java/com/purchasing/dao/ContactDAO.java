package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Contact;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class ContactDAO extends DAOImpl<Contact,Long> {

    @Inject
    public ContactDAO(Session session) {
        super(session);
    }
}

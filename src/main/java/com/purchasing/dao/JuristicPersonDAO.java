package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.JuristicPerson;
import org.hibernate.Session;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class JuristicPersonDAO extends DAOImpl<JuristicPerson,Long> {

    @Inject
    public JuristicPersonDAO(Session session) {
        super(session);
    }

    public JuristicPerson findByCNPJ(String cnpj) {
        return null;
    }
}

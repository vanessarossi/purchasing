package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.JuristicPerson;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
        Criteria criteria = getSession().createCriteria(JuristicPerson.class);
        criteria.add(Restrictions.eq("cnpj", cnpj));
        JuristicPerson juristicPerson = (JuristicPerson) criteria.uniqueResult();
        return juristicPerson;
    }
}

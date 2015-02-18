package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.NaturalPerson;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class NaturalPersonDAO extends DAOImpl<NaturalPerson,Long> {

    @Inject
    public NaturalPersonDAO(Session session) {
        super(session);
    }

    public NaturalPerson findByCPF(String cpf) {
        Criteria criteria = getSession().createCriteria(NaturalPerson.class);
        criteria.add(Restrictions.eq("cpf", cpf));
        NaturalPerson naturalPerson = (NaturalPerson) criteria.uniqueResult();
        return naturalPerson;
    }
}

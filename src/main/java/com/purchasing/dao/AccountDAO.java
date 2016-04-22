package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Account;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by vanessa on 22/04/2016.
 */
public class AccountDAO extends DAOImpl<Account,Long> {

    @Inject
    public AccountDAO(Session session) {
        super(session);
    }

    public List<Account> findByTypeAndCompetenceAndAddress(Account account) {
        Criteria criteria = getSession().createCriteria(Account.class);
            criteria.add(Restrictions.eq("type", account.getType()));
            criteria.add(Restrictions.eq("competence", account.getCompetence()));
            criteria.add(Restrictions.eq("address", account.getAddress()));
        List<Account> accounts = criteria.list();
        return accounts;
    }
}

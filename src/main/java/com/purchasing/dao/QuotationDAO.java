package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Quotation;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class QuotationDAO extends DAOImpl<Quotation,Long> {

    @Inject
    public QuotationDAO(Session session) {
        super(session);
    }

    public List<Quotation> pagination(String sSearch,int iDisplayStart, int iDisplayLength){
        Criteria criteria = getSession().createCriteria(Quotation.class);
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("user","u");
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.addOrder(Order.desc("id"));
        List<Quotation>quotations = new ArrayList<>();
        quotations.addAll(criteria.list());
        return quotations;
    }

    public Integer totalPagination(String sSearch){
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Quotation.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("user","u");
        disjunction.add(Restrictions.ilike("u.name", sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.addOrder(Order.desc("id"));
        criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }
}

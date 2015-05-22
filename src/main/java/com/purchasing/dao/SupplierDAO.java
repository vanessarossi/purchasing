package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Supplier;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class SupplierDAO extends DAOImpl<Supplier,Long> {

    @Inject
    public SupplierDAO(Session session) {
        super(session);
    }

    public List<Supplier> search(String text) {
        Criteria criteria = getSession().createCriteria(Supplier.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("person","person");
            disjunction.add(Restrictions.ilike("person.name", text, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("person.cpf",text, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("person.companyName",text, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("person.cnpj",text, MatchMode.ANYWHERE));
            criteria.add(Restrictions.eq("active",true));
        criteria.add(disjunction);
        List<Supplier>suppliers = new ArrayList<>();
        suppliers.addAll(criteria.list());
        return suppliers;
    }

    public List<Supplier> pagination(String sSearch,int iDisplayStart, int iDisplayLength){
        Criteria criteria = getSession().createCriteria(Supplier.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("person","person");
            disjunction.add(Restrictions.ilike("person.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.asc("id"));
        List<Supplier>suppliers = new ArrayList<>();
        suppliers.addAll(criteria.list());
        return suppliers;
    }

    public Integer totalPagination(String sSearch){
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Supplier.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("person", "person");
            disjunction.add(Restrictions.ilike("person.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }
}

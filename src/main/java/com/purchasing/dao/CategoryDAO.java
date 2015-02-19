package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Category;
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
public class CategoryDAO extends DAOImpl<Category,Long> {

    @Inject
    public CategoryDAO(Session session) {
        super(session);
    }

    public List<Category> pagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(Category.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
        List<Category>categories = new ArrayList<>();
            categories.addAll(criteria.list());
        return categories;
    }

    public Integer totalPagination(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Category.class);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
            total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<Category> findAllOrderName() {
        Criteria criteria = getSession().createCriteria(Category.class);
            criteria.addOrder(Order.asc("description"));
        List<Category>categories = new ArrayList<>();
            categories.addAll(criteria.list());
        return categories;
    }
}

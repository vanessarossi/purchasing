package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Product;
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
public class ProductDAO extends DAOImpl<Product,Long> {

    @Inject
    public ProductDAO(Session session) {
        super(session);
    }

    public Integer totalPagination(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Product.class);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("model",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("mark",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<Product> search(String text) {
        List<Product> products =new ArrayList<>();
        Criteria criteria = getSession().createCriteria(Product.class);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description", text, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("model",text, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("mark", text, MatchMode.ANYWHERE ));
        criteria.add(disjunction);
        criteria.add(Restrictions.eq("active",true));
        products = criteria.list();
        return products;
    }

    public List<Product> pagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(Product.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("model",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("mark",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.addOrder(Order.asc("id"));
        List<Product> products = new ArrayList<>();
        products.addAll(criteria.list());
        return products;
    }

    public List<Product> searchSimilarProduct(Product product){
        List<Product> products = new ArrayList<>();
        Criteria criteria = getSession().createCriteria(Product.class);
        criteria.add(Restrictions.eq("description", product.getDescription()));
        criteria.add(Restrictions.eq("active",true));
        products = criteria.list();
        return products;
    }
}

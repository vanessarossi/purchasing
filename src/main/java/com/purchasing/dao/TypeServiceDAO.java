package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.TypeService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class TypeServiceDAO extends DAOImpl<TypeService,Long> {

    @Inject
    public TypeServiceDAO(Session session) {
        super(session);
    }

    public List<TypeService> pagination(String sSearch,int iDisplayStart, int iDisplayLength){
        Criteria criteria = getSession().createCriteria(TypeService.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
        List<TypeService>typeServices = new ArrayList<>();
        typeServices.addAll(criteria.list());
        return typeServices;
    }

    public Integer totalPagination(String sSearch){
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(TypeService.class);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }
}

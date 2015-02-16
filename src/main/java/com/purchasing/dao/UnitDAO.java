package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Unit;
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
public class UnitDAO extends DAOImpl<Unit,Long> {

    @Inject
    public UnitDAO(Session session) {
        super(session);
    }

    public List<Unit> pagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(Unit.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        List<Unit>units = new ArrayList<>();
        units.addAll(criteria.list());
        return units;
    }

    public Integer totalPagination(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Unit.class);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }
}

package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Company;
import com.purchasing.entity.CostCenter;
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
public class CostCenterDAO extends DAOImpl<CostCenter,Long> {

    @Inject
    public CostCenterDAO(Session session) {
        super(session);
    }

    public List<CostCenter> findByCompany(Company company) {
        List<CostCenter>  costCenters = new ArrayList<>();
        Criteria criteria = getSession().createCriteria(CostCenter.class).add(Restrictions.eq("company", company));
        costCenters = criteria.list();
        return costCenters;
    }

    public List<CostCenter> pagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(CostCenter.class);
            criteria.createAlias("company","c");
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("code",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("c.companyName",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("c.corporateName",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
        List<CostCenter> costCenters = new ArrayList<>();
        costCenters.addAll(criteria.list());
        return costCenters;
    }

    public Integer totalPagination(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(CostCenter.class);
            criteria.createAlias("company","c");
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("description",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("code",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("c.companyName",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("c.corporateName",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }
}

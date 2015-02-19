package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Contract;
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
public class ContractDAO extends DAOImpl<Contract,Long> {

    @Inject
    public ContractDAO(Session session) {
        super(session);
    }

    public List<Contract> pagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(Contract.class);
            criteria.createAlias("supplier","s");
            criteria.createAlias("s.person","p");
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
                disjunction.add(Restrictions.ilike("s.name",sSearch, MatchMode.ANYWHERE));
                criteria.add(disjunction);
        List<Contract> contracts = new ArrayList<>();
        contracts.addAll(criteria.list());
        return contracts;
    }

    public Integer totalPagination(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(CostCenter.class);
            criteria.createAlias("supplier","s");
            criteria.createAlias("s.person","p");
        Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("s.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }
}

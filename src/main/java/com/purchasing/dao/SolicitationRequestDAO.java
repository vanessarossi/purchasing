package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.SolicitationRequest;
import com.purchasing.enumerator.StatusEnum;
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
public class SolicitationRequestDAO extends DAOImpl<SolicitationRequest,Long> {

    @Inject
    public SolicitationRequestDAO(Session session) {
        super(session);
    }

    public List<SolicitationRequest> paginationBySolicitation(Solicitation solicitation, String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(SolicitationRequest.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("product","p");
            disjunction.add(Restrictions.ilike("p.description", sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("p.model",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("p.mark",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.add(Restrictions.isNotNull("product"));
            criteria.add(Restrictions.eq("solicitation",solicitation));
            criteria.addOrder(Order.desc("id"));
        List<SolicitationRequest>solicitationRequests = new ArrayList<>();
        solicitationRequests.addAll(criteria.list());
        return solicitationRequests;
    }

    public Integer totalPaginationBySolicitation(Solicitation solicitation, String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(SolicitationRequest.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("product", "p");
            disjunction.add(Restrictions.ilike("p.description", sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("p.model",sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("p.mark",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.add(Restrictions.isNotNull("product"));
            criteria.add(Restrictions.eq("solicitation", solicitation));
            criteria.addOrder(Order.desc("id"));
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<SolicitationRequest> findSolicitationRequestDelivered(Solicitation solicitation) {
        Criteria criteria = getSession().createCriteria(SolicitationRequest.class);
            criteria.add(Restrictions.eq("solicitation", solicitation));
            criteria.add(Restrictions.isNotNull("deliveryDate"));
            criteria.add(Restrictions.eq("status", StatusEnum.Delivered));
        List<SolicitationRequest>solicitationRequests = new ArrayList<>();
            solicitationRequests.addAll(criteria.list());
        return solicitationRequests;
    }
}

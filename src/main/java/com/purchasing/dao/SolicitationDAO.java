package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.CostCenter;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.User;
import com.purchasing.enumerator.StatusEnum;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
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
public class SolicitationDAO extends DAOImpl<Solicitation,Long> {

    @Inject
    public SolicitationDAO(Session session) {
        super(session);
    }

    public List<Solicitation> pagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(Solicitation.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
            criteria.createAlias("costCenter","cc");
            criteria.createAlias("user","u");

        Disjunction disjunction = Restrictions.disjunction();

        disjunction.add(Restrictions.ilike("cc.description",sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
        List<Solicitation>solicitations = new ArrayList<>();
        solicitations.addAll(criteria.list());
        return solicitations;
    }

    public Integer totalPagination(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("costCenter", "cc");
            criteria.createAlias("user", "u");
            disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("u.name", sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.addOrder(Order.desc("id"));
            criteria.add(disjunction);
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<Solicitation> paginationIndividualCoordinator(String sSearch, int iDisplayStart, int iDisplayLength, List<CostCenter> costCenters) {
        Criteria criteria = getSession().createCriteria(Solicitation.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("costCenter", "cc");
            criteria.createAlias("user", "u");
            disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.add(Restrictions.in("costCenter", costCenters));
            criteria.addOrder(Order.desc("id"));
        List<Solicitation>solicitations = new ArrayList<>();
        solicitations.addAll(criteria.list());
        return solicitations;
    }

    public List<Solicitation> paginationIndividual(String sSearch, int iDisplayStart, int iDisplayLength, User user) {
        Criteria criteria = getSession().createCriteria(Solicitation.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("costCenter","cc");
            criteria.createAlias("user", "u");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.add(Restrictions.eq("user", user));
            criteria.addOrder(Order.desc("id"));
        List<Solicitation>solicitations = new ArrayList<>();
        solicitations.addAll(criteria.list());
        return solicitations;
    }

    public Integer totalIndividualPaginationCoordinator(String sSearch, List<CostCenter> costCenters) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("costCenter", "cc");
        criteria.createAlias("user","u");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.add(Restrictions.in("costCenter", costCenters));
        criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public Integer totalIndividualPagination(String sSearch, User user) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("costCenter","cc");
        criteria.createAlias("user", "u");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.add(Restrictions.eq("user", user));
        criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<Solicitation> paginationWithFilter(String sSearch,StatusEnum status ,int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
        criteria.createAlias("costCenter","cc");
        criteria.createAlias("user", "u");
        criteria.createAlias("situation", "situation");
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.ilike("cc.description",sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.add(Restrictions.eq("situation.status", status));
        criteria.addOrder(Order.desc("id"));
        List<Solicitation>solicitations = new ArrayList<>();
        solicitations.addAll(criteria.list());
        return solicitations;
    }

    public Integer totalPaginationWithFilter(String sSearch,StatusEnum status) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("costCenter", "cc");
        criteria.createAlias("user", "u");
        criteria.createAlias("situation", "situation");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name", sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.add(Restrictions.eq("situation.status", status));
        criteria.addOrder(Order.desc("id"));
        criteria.add(disjunction);
        criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<Solicitation> paginationIndividualCoordinatorWithFilter(String sSearch,StatusEnum status ,int iDisplayStart, int iDisplayLength, List<CostCenter> costCenters) {
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("costCenter","cc");
        criteria.createAlias("user", "u");
        criteria.createAlias("situation", "situation");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.add(Restrictions.eq("situation.status", status));
        criteria.add(Restrictions.in("costCenter", costCenters));
        criteria.addOrder(Order.desc("id"));
        List<Solicitation>solicitations = new ArrayList<>();
        solicitations.addAll(criteria.list());
        return solicitations;
    }

    public Integer totalIndividualPaginationCoordinatorWithFilter(String sSearch, StatusEnum status,List<CostCenter> costCenters) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("costCenter","cc");
        criteria.createAlias("user","u");
        criteria.createAlias("situation", "situation");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.add(Restrictions.eq("situation.status", status));
        criteria.add(Restrictions.in("costCenter", costCenters));
        criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<Solicitation> paginationIndividualWithFilter(String sSearch, StatusEnum status,int iDisplayStart, int iDisplayLength, User user) {
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("costCenter","cc");
        criteria.createAlias("user", "u");
        criteria.createAlias("situation", "situation");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.add(Restrictions.eq("situation.status", status));
        criteria.add(Restrictions.eq("user", user));
        criteria.addOrder(Order.desc("id"));
        List<Solicitation>solicitations = new ArrayList<>();
        solicitations.addAll(criteria.list());
        return solicitations;
    }

    public Integer totalIndividualPaginationWithFilter(String sSearch,StatusEnum status ,User user) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.createAlias("costCenter","cc");
        criteria.createAlias("user", "u");
        criteria.createAlias("situation", "situation");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
        criteria.add(Restrictions.eq("situation.status", status));
            criteria.add(Restrictions.eq("user", user));
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<Solicitation> paginationMissingAnalyst(String sSearch, int iDisplayStart, int iDisplayLength, List<CostCenter> costCenters) {
        Criteria criteria = getSession().createCriteria(Solicitation.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);
        List<StatusEnum>status = new ArrayList<>();
            status.add(StatusEnum.CancellationRequest);
            status.add(StatusEnum.Approved);

        Criterion criterion = Restrictions.and(Restrictions.in("status", status));
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("costCenter","cc");
            criteria.createAlias("user","u");
            criteria.createAlias("situation","s");
            disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);

        Criterion waitingApproval = Restrictions.and(Restrictions.in("costCenter", costCenters), Restrictions.eq("s.status", StatusEnum.WaitingApproval));
        Criterion cancellationAndApproved = Restrictions.and(Restrictions.in("s.status", status));
        criteria.add(Restrictions.or(waitingApproval,cancellationAndApproved));

        criteria.addOrder(Order.asc("id"));
        List<Solicitation>solicitations = new ArrayList<>();
        solicitations.addAll(criteria.list());
        return solicitations;
    }

    public List<Solicitation> paginationMissingCoordinator(String sSearch, int iDisplayStart, int iDisplayLength, List<CostCenter> costCenters) {
        Criteria criteria = getSession().createCriteria(Solicitation.class);
            criteria.setFirstResult(iDisplayStart);
            criteria.setMaxResults(iDisplayLength);

        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("costCenter","cc");
            criteria.createAlias("user", "u");
            criteria.createAlias("situation", "s");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.add(Restrictions.in("costCenter", costCenters));
            criteria.add(Restrictions.eq("s.status", StatusEnum.WaitingApproval));

            criteria.addOrder(Order.asc("id"));
        List<Solicitation>solicitations = new ArrayList<>();
        solicitations.addAll(criteria.list());
        return solicitations;
    }

    public Integer totalMissingPaginationAnalyst(String sSearch, List<CostCenter> costCenters) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Solicitation.class);
        List<StatusEnum>status = new ArrayList<>();
            status.add(StatusEnum.CancellationRequest);
            status.add(StatusEnum.Approved);
        Criterion criterion = Restrictions.and(Restrictions.in("status", status));
        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("costCenter", "cc");
            criteria.createAlias("user", "u");
            criteria.createAlias("situation", "s");
            disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);

        Criterion waitingApproval = Restrictions.and(Restrictions.in("costCenter", costCenters), Restrictions.eq("s.status", StatusEnum.WaitingApproval));
        Criterion cancellationAndApproved = Restrictions.and(Restrictions.in("s.status", status));
            criteria.add(Restrictions.or(waitingApproval, cancellationAndApproved));

        criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public Integer totalMissingPaginationCoordinator(String sSearch, List<CostCenter> costCenters) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(Solicitation.class);

        Disjunction disjunction = Restrictions.disjunction();
            criteria.createAlias("costCenter","cc");
            criteria.createAlias("user", "u");
            criteria.createAlias("situation", "s");
        disjunction.add(Restrictions.ilike("cc.description", sSearch, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.ilike("u.name",sSearch, MatchMode.ANYWHERE));
            criteria.add(disjunction);
            criteria.add(Restrictions.in("costCenter", costCenters));
            criteria.add(Restrictions.eq("s.status", StatusEnum.WaitingApproval));
            criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public Solicitation findByApprovedPartiallyDelivered(Solicitation solicitation){
        List<StatusEnum>status = new ArrayList<>();
            status.add(StatusEnum.PartiallyFinished);
            status.add(StatusEnum.Approved);
        Criteria criteria = getSession().createCriteria(Solicitation.class);
            criteria.add(Restrictions.eq("id", solicitation.getId()));
            criteria.createAlias("situation","s");
        Criterion criterion = Restrictions.and(Restrictions.in("s.status", status));
            criteria.add(criterion);
        Solicitation solicitationFound = (Solicitation) criteria.uniqueResult();
        return solicitationFound;
    }

}

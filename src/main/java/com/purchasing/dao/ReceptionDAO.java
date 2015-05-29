package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Reception;
import com.purchasing.enumerator.StatusEnum;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
public class ReceptionDAO extends DAOImpl<Reception,Long> {

    @Inject
    public ReceptionDAO(Session session) {
        super(session);
    }

    public List<Reception> paginationMissingConfered(String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(Reception.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);
        criteria.add(Restrictions.eq("status", StatusEnum.Conferred));
        criteria.addOrder(Order.desc("id"));
        List<Reception> receptions = new ArrayList<>();
        receptions.addAll(criteria.list());
        return receptions;
    }

    public Integer totalPaginationMissingConfered(String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(PurchaseOrder.class);
        Disjunction disjunction = Restrictions.disjunction();
        criteria.add(Restrictions.eq("status", StatusEnum.Conferred));
        criteria.addOrder(Order.desc("id"));
        criteria.add(disjunction);
        criteria.setProjection(Projections.rowCount());
        total = Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }

    public List<Reception> searchByDate(Date initialDate , Date finalDate) {
        Timestamp timestamp = new Timestamp(finalDate.getTime());
        timestamp.setHours(23);
        timestamp.setMinutes(59);
        timestamp.setSeconds(59);

        Criteria criteria = getSession().createCriteria(Reception.class);
                criteria.createAlias("paymentInformation","paymentInformation");
                criteria.add(Restrictions.between("date",initialDate,timestamp));
        criteria.addOrder(Order.asc("paymentInformation.expirationDate"));
        List<Reception> receptions = new ArrayList<>();
        receptions.addAll(criteria.list());
        return receptions;
    }


}

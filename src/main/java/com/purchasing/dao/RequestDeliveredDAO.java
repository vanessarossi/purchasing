package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.OrderRequest;
import com.purchasing.entity.RequestDelivered;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;

/**
 * @author vanessa
 */
public class RequestDeliveredDAO extends DAOImpl<RequestDelivered,Long> {

    @Inject
    public RequestDeliveredDAO(Session session) {
        super(session);
    }

    public Float totalDeliveredByOrderRequest(OrderRequest orderRequest) {
        Float total = 0f;
        Criteria criteria = getSession().createCriteria(RequestDelivered.class);
        criteria.add(Restrictions.eq("orderRequest",orderRequest));
        criteria.setProjection(Projections.sum("quantity"));
        Object object =  criteria.uniqueResult();
        total = Float.parseFloat(object == null ? "0" : object.toString());
        return total;
    }

}

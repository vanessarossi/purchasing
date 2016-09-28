package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.OrderRequest;
import com.purchasing.entity.Product;
import com.purchasing.entity.RequestDelivered;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public Float totalDeliveredByOrderRequestService(OrderRequest orderRequest) {
        Float total = 0f;
        Criteria criteria = getSession().createCriteria(RequestDelivered.class);
        criteria.add(Restrictions.eq("orderRequest",orderRequest));
        criteria.setProjection(Projections.sum("quantity"));
        Object object =  criteria.uniqueResult();
        total = Float.parseFloat(object == null ? "1" : object.toString());
        return total;
    }

    public BigDecimal totalPriceServiceDelivered(OrderRequest orderRequest){
        BigDecimal total;
        Criteria criteria = getSession().createCriteria(RequestDelivered.class);
        criteria.add(Restrictions.eq("orderRequest",orderRequest));
        criteria.setProjection(Projections.sum("price"));
        Object object =  criteria.uniqueResult();
        total = new BigDecimal(object == null ? "0" : object.toString());
        return total;
    }

    public List<RequestDelivered> paginationByProduct(Product product,String sSearch, int iDisplayStart, int iDisplayLength) {
        Criteria criteria = getSession().createCriteria(RequestDelivered.class);

            criteria.createAlias("orderRequest","orderRequest");
            criteria.createAlias("orderRequest.purchaseOrder","purchaseOrder");
            criteria.createAlias("orderRequest.budgetQuotation","budgetQuotation");

            criteria.createAlias("budgetQuotation.budget","budget");
            criteria.createAlias("budget.supplier","supplier");
            criteria.createAlias("supplier.person","person");

            criteria.createAlias("budgetQuotation.quotationRequest","quotationRequest");
            criteria.createAlias("quotationRequest.solicitationRequest","solicitationRequest");


        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.ilike("person.name",sSearch, MatchMode.ANYWHERE));

        criteria.add(disjunction);


        criteria.add(Restrictions.eq("solicitationRequest.product",product));
        criteria.addOrder(Order.desc("purchaseOrder.dateGenerate"));

        criteria.setFirstResult(iDisplayStart);
        criteria.setMaxResults(iDisplayLength);


        List<RequestDelivered> requestsDelivered = new ArrayList<>();
        requestsDelivered.addAll(criteria.list());
        return requestsDelivered;
    }

    public Integer totalPaginationByProduct(Product product, String sSearch) {
        Integer total = 0;
        Criteria criteria = getSession().createCriteria(RequestDelivered.class);

        criteria.createAlias("orderRequest","orderRequest");
        criteria.createAlias("orderRequest.purchaseOrder","purchaseOrder");
        criteria.createAlias("orderRequest.budgetQuotation","budgetQuotation");

        criteria.createAlias("budgetQuotation.budget","budget");
        criteria.createAlias("budget.supplier","supplier");
        criteria.createAlias("supplier.person","person");

        criteria.createAlias("budgetQuotation.quotationRequest","quotationRequest");
        criteria.createAlias("quotationRequest.solicitationRequest","solicitationRequest");


        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.ilike("person.name",sSearch, MatchMode.ANYWHERE));

        criteria.add(disjunction);


        criteria.add(Restrictions.eq("solicitationRequest.product",product));
        criteria.addOrder(Order.desc("purchaseOrder.dateGenerate"));
        criteria.setProjection(Projections.rowCount());
        total =  Integer.parseInt(criteria.uniqueResult().toString());
        return total;
    }


}

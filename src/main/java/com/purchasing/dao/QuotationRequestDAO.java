package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import com.purchasing.entity.Product;
import com.purchasing.entity.Quotation;
import com.purchasing.entity.QuotationRequest;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
public class QuotationRequestDAO extends DAOImpl<QuotationRequest,Long> {

    @Inject
    public QuotationRequestDAO(Session session) {
        super(session);
    }

    public List<QuotationRequest> findQuotationRequestProduct(Quotation quotation){
            Criteria criteria = getSession().createCriteria(QuotationRequest.class);
                     criteria.createAlias("solicitationRequest","sr");
                     criteria.add(Restrictions.isNotNull("sr.product"));
                     criteria.add(Restrictions.eq("quotation",quotation));
            List<QuotationRequest>quotationRequests = new ArrayList<>();
            quotationRequests.addAll(criteria.list());
        return quotationRequests;
    }

    public List<QuotationRequest> findQuotationRequestService(Quotation quotation){
            Criteria criteria = getSession().createCriteria(QuotationRequest.class);
            criteria.createAlias("solicitationRequest","sr");
            criteria.add(Restrictions.isNotNull("sr.service"));
            criteria.add(Restrictions.eq("quotation",quotation));
            List<QuotationRequest>quotationRequests = new ArrayList<>();
            quotationRequests.addAll(criteria.list());
        return quotationRequests;
    }

    public List<QuotationRequest> findQuotationRequestProductByProduct(Quotation quotation,Product product){
        Criteria criteria = getSession().createCriteria(QuotationRequest.class);
        criteria.createAlias("solicitationRequest","sr");
        criteria.add(Restrictions.eq("sr.product", product));
        criteria.add(Restrictions.eq("quotation",quotation));
        List<QuotationRequest>quotationRequests = new ArrayList<>();
        quotationRequests.addAll(criteria.list());
        return quotationRequests;
    }


}

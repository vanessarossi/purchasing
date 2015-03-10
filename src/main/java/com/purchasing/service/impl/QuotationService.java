package com.purchasing.service.impl;

import com.purchasing.entity.Product;
import com.purchasing.entity.Quotation;
import com.purchasing.entity.QuotationRequest;
import com.purchasing.entity.SolicitationRequest;
import com.purchasing.support.quotation.QuotationRequestProductView;

import java.util.List;

/**
 * @author vanessa
 */
public interface QuotationService {

    public Quotation save(Quotation quotation);

    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    public Quotation searchById(Quotation quotation);

    public Quotation searchOpenById(Quotation quotation);

    public void addQuotationRequestProduct(Quotation quotation,List<SolicitationRequest> solicitationRequests);
    public void addQuotationRequestService(Quotation quotation,SolicitationRequest solicitationRequest);

    public void removeQuotationRequestByProduct(Quotation quotation,Product product);
    public void removeQuotationRequest(QuotationRequest quotationRequest);

    public List<QuotationRequest> searchQuotationRequestProductByQuotation(Quotation quotation);

    public List<QuotationRequest> searchQuotationRequestProductByProduct(Quotation quotation, Product product);

    public List<QuotationRequest> searchQuotationRequestServiceByQuotation(Quotation quotation);

    public List<QuotationRequestProductView> groupByProduct(Quotation quotation);
}

package com.purchasing.service.impl;

import com.purchasing.entity.*;

import java.util.List;

/**
 * @author vanessa
 */
public interface QuotationService {

    public Quotation save(Quotation quotation);

    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    public Quotation searchById(Quotation quotation);

    public void addQuotationRequestProduct(Quotation quotation,List<SolicitationRequest> solicitationRequests);
    public void addQuotationRequestService(Quotation quotation,SolicitationRequest solicitationRequest);

    public void removeQuotationRequestByProduct(Quotation quotation,Product product);
    public void removeQuotationRequest(QuotationRequest quotationRequest);

    public List<QuotationRequest> searchQuotationRequestProductByQuotation(Quotation quotation);
    public List<QuotationRequest> searchQuotationRequestProductByProduct(Product product);
    public List<QuotationRequest> searchQuotationRequestServiceByQuotation(Quotation quotation);

    public List<QuotationRequest> groupByProduct(Quotation quotation);
}

package com.purchasing.service.impl;

import com.purchasing.entity.*;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.support.quotation.QuotationRequestProductView;

import java.io.File;
import java.util.List;

/**
 * @author vanessa
 */
public interface QuotationService {

    public Quotation save(Quotation quotation);
    public Quotation saveObservation(Quotation quotation);

    public void saveCancellation(Quotation quotation);

    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    public List<Object[]> findPaginationWithFilter(String sSearch,StatusEnum status,int iDisplayStart, int iDisplayLength);
    public Integer totalPaginationWithFilter(String sSearch,StatusEnum status);

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

    public File printer(Quotation quotation);
}

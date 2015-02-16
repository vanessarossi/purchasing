package com.purchasing.service.impl;

import com.purchasing.entity.Product;
import com.purchasing.entity.Quotation;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.SolicitationRequest;

import java.util.List;

/**
 * @author vanessa
 */
public interface QuotationService {

    public Quotation save(Quotation quotation);
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);

    public List<SolicitationRequest> searchRequestSolicitationByProduct(Product product);

    public List<SolicitationRequest>searchRequestSolicitationBySoliciation(Solicitation solicitation);

    public SolicitationRequest searchRequestSolicitationServiceBySoliciation(Solicitation solicitation);

    public void addQuotationRequestProduct(Quotation quotation,List<SolicitationRequest> solicitationRequests);
    public void addQuotationRequestService(Quotation quotation,SolicitationRequest solicitationRequest);
}

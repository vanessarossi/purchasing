package com.purchasing.service;

import com.purchasing.entity.Product;
import com.purchasing.entity.Quotation;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.SolicitationRequest;
import com.purchasing.service.impl.QuotationService;

import java.util.List;

/**
 * @author vanessa
 */
public class QuotationServiceImpl implements QuotationService {

    @Override
    public Quotation save(Quotation quotation) {
        return null;
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        return null;
    }

    @Override
    public List<SolicitationRequest> searchRequestSolicitationByProduct(Product product) {
        return null;
    }

    @Override
    public List<SolicitationRequest> searchRequestSolicitationBySoliciation(Solicitation solicitation) {
        return null;
    }

    @Override
    public SolicitationRequest searchRequestSolicitationServiceBySoliciation(Solicitation solicitation) {
        return null;
    }

    @Override
    public void addQuotationRequestProduct(Quotation quotation, List<SolicitationRequest> solicitationRequests) {

    }

    @Override
    public void addQuotationRequestService(Quotation quotation, SolicitationRequest solicitationRequest) {

    }
}

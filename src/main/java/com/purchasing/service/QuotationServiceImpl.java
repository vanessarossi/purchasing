package com.purchasing.service;

import com.purchasing.entity.Budget;
import com.purchasing.entity.PaymentInformation;
import com.purchasing.entity.Product;
import com.purchasing.entity.Quotation;
import com.purchasing.entity.QuotationRequest;
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
    public Quotation searchById(Quotation quotation) {
        return null;
    }

    @Override
    public void addQuotationRequestProduct(Quotation quotation, List<SolicitationRequest> solicitationRequests) {

    }

    @Override
    public void addQuotationRequestService(Quotation quotation, SolicitationRequest solicitationRequest) {

    }

    @Override
    public void removeQuotationRequestByProduct(Quotation quotation, Product product) {

    }

    @Override
    public void removeQuotationRequest(QuotationRequest quotationRequest) {

    }

    @Override
    public List<QuotationRequest> searchQuotationRequestProductByQuotation(Quotation quotation) {
        return null;
    }

    @Override
    public List<QuotationRequest> searchQuotationRequestProductByProduct(Product product) {
        return null;
    }

    @Override
    public List<QuotationRequest> searchQuotationRequestServiceByQuotation(Quotation quotation) {
        return null;
    }

    @Override
    public List<QuotationRequest> groupByProduct(Quotation quotation) {
        return null;
    }

    @Override
    public void saveBudget(Budget budget) {

    }

    @Override
    public Budget searchBudget(Budget budget) {
        return null;
    }

    @Override
    public void removeBudget(Budget budget) {

    }

    @Override
    public PaymentInformation findPaymentInformationById(PaymentInformation paymentInformation) {
        return null;
    }
}

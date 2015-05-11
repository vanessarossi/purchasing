package com.purchasing.service.impl;

import com.purchasing.entity.*;
import com.purchasing.enumerator.StatusEnum;

import java.io.File;
import java.util.List;

/**
 * @author vanessa
 */
public interface SolicitationService {

    public User getUserLogged();

    public Solicitation save(Solicitation solicitation);
    public Solicitation copy (Solicitation solicitation);
    public Solicitation searchById(Solicitation solicitation);
    public File generateFile(Solicitation solicitation);
    public void removeSolicitationRequest(SolicitationRequest solicitationRequest);

    /** Pagination **/
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPagination(String sSearch);
    public List<Object[]> findIndividualPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalIndividualPagination(String sSearch);
    public List<Object[]> findMissingPagination(String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalMissingPagination(String sSearch);
    public List<Object[]> findPaginationSolicitationRequests(Solicitation solicitation,String sSearch, int iDisplayStart, int iDisplayLength);
    public Integer totalPaginationSolicitationRequests(Solicitation solicitation,String sSearch);

    public List<Object[]> findPaginationWithFilter(String sSearch,StatusEnum status , int iDisplayStart, int iDisplayLength);
    public Integer totalPaginationWithFilter(String sSearch,StatusEnum status);

    public List<Object[]> findIndividualPaginationWithFilter(String sSearch,StatusEnum status, int iDisplayStart, int iDisplayLength);
    public Integer totalIndividualPaginationWithFilter(String sSearch,StatusEnum status);

    /** Status  **/
    public void approval(Solicitation solicitation);
    public void reject(Solicitation solicitation);
    public void preAnalysisReject(Solicitation solicitation);
    public void cancellationRequest(Solicitation solicitation);
    public void alterStatus(Solicitation solicitation,StatusEnum statusEnum);
    public void cancel (Solicitation solicitation);
    public void finalize(Solicitation solicitation);

    /** Help in quotation  **/
    public List<SolicitationRequest> searchSolicitationRequestProductByProduct(Product product);
    public List<SolicitationRequest> searchSolicitationRequestProductBySolicitation(Solicitation solicitation);
    public SolicitationRequest searchSolicitationRequestServiceBySolicitation(Solicitation solicitation);

    /** Help finalization **/
    public Solicitation findByApprovedPartiallyDelivered(Solicitation solicitation);


    /**  Justificativas **/

    public List<PurchaseOrder> getPurchaseOrdersWithJustification(Solicitation solicitation);
    public List<Quotation> getQuotationCancelledWithJustification(Solicitation solicitation);
}

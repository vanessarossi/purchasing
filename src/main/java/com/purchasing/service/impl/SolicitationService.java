package com.purchasing.service.impl;

import com.purchasing.entity.Product;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.SolicitationRequest;
import com.purchasing.enumerator.StatusEnum;

import java.io.File;
import java.util.List;

/**
 * @author vanessa
 */
public interface SolicitationService {

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

    /** Status  **/
    public void approval(Solicitation solicitation);
    public void reject(Solicitation solicitation);
    public void preAnalysisReject(Solicitation solicitation);
    public void cancellationRequest(Solicitation solicitation);
    public void alterStatus(Solicitation solicitation,StatusEnum statusEnum);
    public void cancel (Solicitation solicitation);
    public void finalize(Solicitation solicitation);

    /** Help in quotation  **/
    public List<SolicitationRequest> searchSolicitationRequestByProduct(Product product);
    public List<SolicitationRequest> searchSolicitationRequestBySolicitation(Solicitation solicitation);

}

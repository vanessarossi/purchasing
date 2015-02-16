package com.purchasing.service;

import com.purchasing.dao.CostCenterDAO;
import com.purchasing.dao.ServiceDAO;
import com.purchasing.dao.SituationDAO;
import com.purchasing.dao.SolicitationDAO;
import com.purchasing.dao.SolicitationRequestDAO;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.SolicitationRequest;
import com.purchasing.entity.User;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.service.impl.SolicitationService;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

/**
 * @author vanessa
 */
public class SolicitationServiceImpl implements SolicitationService {

    @Inject private HttpSession httpSession;
   // @Inject private SolicitationPrinter solicitationPrinter;
    @Inject private SituationDAO situationDAO;
    @Inject private SolicitationDAO solicitationDAO;
    @Inject private SolicitationRequestDAO solicitationRequestDAO;
    @Inject private ServiceDAO serviceDAO;
    @Inject private CostCenterDAO costCenterDAO;



    public User getUserLogged(){
        User user = (User) httpSession.getAttribute("userLogged");
        return  user;
    }

    @Override
    public Solicitation save(Solicitation solicitation) {
        return null;
    }

    @Override
    public Solicitation copy(Solicitation solicitation) {
        return null;
    }

    @Override
    public Solicitation searchById(Solicitation solicitation) {
        return null;
    }

    @Override
    public File generateFile(Solicitation solicitation) {
        return null;
    }

    @Override
    public void removeProduct(SolicitationRequest solicitationRequest) {

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
    public List<Object[]> findIndividualPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    @Override
    public Integer totalIndividualPagination(String sSearch) {
        return null;
    }

    @Override
    public List<Object[]> findMissingPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    @Override
    public Integer totalMissingPagination(String sSearch) {
        return null;
    }

    @Override
    public List<Object[]> findPaginationSolicitationRequests(Solicitation solicitation, String sSearch, int iDisplayStart, int iDisplayLength) {
        return null;
    }

    @Override
    public Integer totalPaginationSolicitationRequests(Solicitation solicitation, String sSearch) {
        return null;
    }

    @Override
    public void approval(Solicitation solicitation) {

    }

    @Override
    public void reject(Solicitation solicitation) {

    }

    @Override
    public void preAnalysisReject(Solicitation solicitation) {

    }

    @Override
    public void cancellationRequest(Solicitation solicitation) {

    }

    @Override
    public void alterStatus(Solicitation solicitation, StatusEnum statusEnum) {

    }

    @Override
    public void cancel(Solicitation solicitation) {

    }

    @Override
    public void finalize(Solicitation solicitation) {

    }
}

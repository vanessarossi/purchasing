package com.purchasing.service;

import com.purchasing.dao.*;
import com.purchasing.entity.*;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.printer.OrderBudgetPrinter;
import com.purchasing.service.impl.QuotationService;
import com.purchasing.support.date.Conversor;
import com.purchasing.support.quotation.QuotationRequestProductView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author vanessa
 */
public class QuotationServiceImpl implements QuotationService {

    @Inject private HttpSession httpSession;
    @Inject private QuotationDAO quotationDAO;
    @Inject private SolicitationRequestDAO solicitationRequestDAO;
    @Inject private QuotationRequestDAO quotationRequestDAO;
    @Inject private SolicitationDAO solicitationDAO;
    @Inject private SituationDAO situationDAO;
    @Inject private OrderBudgetPrinter orderBudgetPrinter;

    @Override
    public Quotation save(Quotation quotation) {
        quotation.setUser(getUserLogged());
        quotation.setInitialDate(new Timestamp(new Date().getTime()));
        quotation.setUser(getUserLogged());
        quotation.setStatus(StatusEnum.Open);
        Quotation quotationSaved = quotationDAO.save(quotation);
        return quotationSaved;
    }

    @Override
    public Quotation saveObservation(Quotation quotation) {
        Quotation quotationFound = quotationDAO.findById(Quotation.class, quotation.getId());
        quotationFound.setObservation(quotation.getObservation());
        quotation = quotationDAO.save(quotationFound);
        return quotation;
    }

    @Override
    public void saveCancellation(Quotation quotation) {
        Quotation quotationFound = quotationDAO.findById(Quotation.class,quotation.getId());
        if (quotationFound.getStatus() != StatusEnum.Finished){
            quotationFound.setJustificationCancellation(quotation.getJustificationCancellation());
            quotationFound.setFinalDate(new Timestamp(new Date().getTime()));
            quotationFound.setStatus(StatusEnum.Canceled);
            quotationDAO.save(quotationFound);
            List<SolicitationRequest> solicitationRequests = new ArrayList<>();

            List<QuotationRequest> quotationRequests =  quotationFound.getQuotationRequests();
            for (QuotationRequest quotationRequest : quotationRequests){
                solicitationRequests.add(quotationRequest.getSolicitationRequest());
            }
            for (SolicitationRequest solicitationRequest : solicitationRequests){
                Solicitation solicitation = solicitationRequest.getSolicitation();
                Situation situation =  solicitation.getSituation();
                situation.setStatus(StatusEnum.QuotationCanceled);
                situationDAO.save(situation);
            }
        }
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Quotation> quotations = quotationDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> quotationList = new ArrayList<>();

        for (Quotation quotation : quotations){
            String colNumber = quotation.getId().toString();
            String colType = quotation.getType().getDescription();
            String colUser = quotation.getUser().getName();
            String colStatus = quotation.getStatus().getDescription();
            String colInitialDate = Conversor.converterDateTimeInString(quotation.getInitialDate());
            String colFinalDate = Conversor.converterDateTimeInString(quotation.getFinalDate());
            String buttonView = "<a href=/purchasing/cotacao/editar/"+quotation.getId()+"><span class=\"fa fa-pencil-square-o btn btn-default btn-xs\"></span></a>";
            String buttonCancel = "<a onclick=openFormCancellation("+quotation.getId()+")><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colNumber,
                    colType,
                    colUser,
                    colStatus,
                    colInitialDate,
                    colFinalDate,
                    buttonView,
                    buttonCancel
            };
            quotationList.add(row);
        }
        return quotationList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        return quotationDAO.totalPagination(search);
    }

    @Override
    public List<Object[]> findPaginationWithFilter(String sSearch, StatusEnum status, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Quotation> quotations = quotationDAO.paginationWithFilter(search,status,iDisplayStart,iDisplayLength);
        List<Object[]> quotationList = new ArrayList<>();

        for (Quotation quotation : quotations){
            String colNumber = quotation.getId().toString();
            String colType = quotation.getType().getDescription();
            String colUser = quotation.getUser().getName();
            String colStatus = quotation.getStatus().getDescription();
            String colInitialDate = Conversor.converterDateTimeInString(quotation.getInitialDate());
            String colFinalDate = Conversor.converterDateTimeInString(quotation.getFinalDate());
            String buttonView = "<a href=/purchasing/cotacao/editar/"+quotation.getId()+"><span class=\"fa fa-pencil-square-o btn btn-default btn-xs\"></span></a>";
            String buttonCancel = "<a onclick=openFormCancellation("+quotation.getId()+")><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colNumber,
                    colType,
                    colUser,
                    colStatus,
                    colInitialDate,
                    colFinalDate,
                    buttonView,
                    buttonCancel
            };
            quotationList.add(row);
        }
        return quotationList;
    }

    @Override
    public Integer totalPaginationWithFilter(String sSearch, StatusEnum status) {
        String search = sSearch == null ? "" : sSearch;
        return quotationDAO.totalPaginationWithFilter(search,status);
    }

    @Override
    public Quotation searchById(Quotation quotation) {
        if (quotation.getId() != null){
            quotation  = quotationDAO.findById(Quotation.class, quotation.getId());
        }else{
            quotation = new Quotation();
        }
        return  quotation;
    }

    @Override
    public Quotation searchOpenById(Quotation quotation) {
        if (quotation.getId() != null){
            quotation  = quotationDAO.findOpenById(quotation);
        }else{
            quotation = new Quotation();
        }
        return  quotation;
    }

    @Override
    public void addQuotationRequestProduct(Quotation quotation, List<SolicitationRequest> solicitationRequests) {
        for (SolicitationRequest solicitationRequest : solicitationRequests){
            if (solicitationRequest.getAddQuotation() != null && solicitationRequest.getAddQuotation() == true){
                QuotationRequest quotationRequest = new QuotationRequest();
                quotationRequest.setQuotation(quotation);
                quotationRequest.setSolicitationRequest(solicitationRequest);
                quotationRequestDAO.save(quotationRequest);

                SolicitationRequest solicitationRequestFound = solicitationRequestDAO.findById(SolicitationRequest.class,solicitationRequest.getId());
                solicitationRequestFound.setAddQuotation(true);
                solicitationRequestDAO.save(solicitationRequestFound);

                updateStatusSituationSolicitation(solicitationRequestFound.getSolicitation());
            }
        }
    }

    @Override
    public void addQuotationRequestService(Quotation quotation, SolicitationRequest solicitationRequest) {
        if (solicitationRequest.getAddQuotation() != null && solicitationRequest.getAddQuotation() == true) {
            QuotationRequest quotationRequest = new QuotationRequest();
            quotationRequest.setQuotation(quotation);
            quotationRequest.setSolicitationRequest(solicitationRequest);
            quotationRequestDAO.save(quotationRequest);

            SolicitationRequest solicitationRequestFound = solicitationRequestDAO.findById(SolicitationRequest.class, solicitationRequest.getId());

            solicitationRequestFound.setAddQuotation(true);
            solicitationRequestDAO.save(solicitationRequestFound);

            updateStatusSituationSolicitation(solicitationRequestFound.getSolicitation());
        }
    }

    @Override
    public void removeQuotationRequestByProduct(Quotation quotation, Product product) {
        List<QuotationRequest> quotationRequests = searchQuotationRequestProductByProduct(quotation, product);
        for (QuotationRequest quotationRequest : quotationRequests){
            SolicitationRequest solicitationRequest = quotationRequest.getSolicitationRequest();
            solicitationRequest.setAddQuotation(false);
            solicitationRequestDAO.save(solicitationRequest);

            quotationRequestDAO.delete(quotationRequest);

            updateStatusSituationSolicitation(solicitationRequest.getSolicitation());
        }
    }

    @Override
    public void removeQuotationRequest(QuotationRequest quotationRequest) {
        QuotationRequest quotationRequestFound = quotationRequestDAO.findById(QuotationRequest.class, quotationRequest.getId());
        SolicitationRequest solicitationRequestFound = quotationRequestFound.getSolicitationRequest();
            solicitationRequestFound.setAddQuotation(false);
        solicitationRequestDAO.save(solicitationRequestFound);
        quotationRequestDAO.delete(quotationRequestFound);
        updateStatusSituationSolicitation(solicitationRequestFound.getSolicitation());
    }

    @Override
    public List<QuotationRequest> searchQuotationRequestProductByQuotation(Quotation quotation) {
        return quotationRequestDAO.findQuotationRequestProduct(quotation);
    }

    @Override
    public List<QuotationRequest> searchQuotationRequestServiceByQuotation(Quotation quotation) {
        return quotationRequestDAO.findQuotationRequestService(quotation);
    }

    @Override
    public List<QuotationRequest> searchQuotationRequestProductByProduct(Quotation quotation, Product product) {
        return quotationRequestDAO.findQuotationRequestProductByProduct(quotation, product);
    }

    @Override
    public List<QuotationRequestProductView> groupByProduct(Quotation quotation) {
        quotation = quotationDAO.findById(Quotation.class,quotation.getId());
        List<QuotationRequest> quotationRequests = searchQuotationRequestProductByQuotation(quotation);
        List<QuotationRequestProductView> quotationRequestProductViewList = new ArrayList<>();
        List<QuotationRequestProductView> quotationRequestProductViews = new ArrayList<>();

        Collections.sort(quotationRequestProductViews,new QuotationRequestProductView());
        Map<Long,QuotationRequestProductView> map = new HashMap<>();

            quotationRequestProductViewList = new QuotationRequestProductView().generateList(quotationRequests);

            for (QuotationRequestProductView quotationRequestProductView : quotationRequestProductViewList){
                Long idProduct = quotationRequestProductView.getProduct().getId();
                if (!map.containsKey(idProduct)){
                    map.put(quotationRequestProductView.getProduct().getId(),quotationRequestProductView);
                }else{
                    QuotationRequestProductView quotationRequestProductV = map.get(idProduct);
                    if (quotationRequestProductView.getQuantity() == null){
                        quotationRequestProductView.setQuantity(0f);
                    }
                    BigDecimal quantity = (  new BigDecimal(quotationRequestProductView.getQuantity().toString()).add(new BigDecimal(quotationRequestProductV.getQuantity().toString()))    );
                    quotationRequestProductV.setQuantity(Float.parseFloat(quantity.toString()));
                }
            }

        for (Long key : map.keySet()){
            QuotationRequestProductView quotationRequestProductView = map.get(key);
            quotationRequestProductViews.add(quotationRequestProductView);
        }

        return quotationRequestProductViews;
    }

    @Override
    public File printer(Quotation quotation) {
        quotation = quotationDAO.findById(Quotation.class, quotation.getId());
        return orderBudgetPrinter.generateOrderBudget(quotation.getId(),this);
    }

    @Override
    public void updateChangedProduct(Product oldProduct, Product newProduct, Quotation quotation) {
        List<QuotationRequest> quotationRequests = quotationRequestDAO.findQuotationRequestProductByProduct(quotation,oldProduct);
        for (QuotationRequest quotationRequest : quotationRequests){
            SolicitationRequest solicitationRequest = new SolicitationRequest();
            solicitationRequest = quotationRequest.getSolicitationRequest();
            solicitationRequest.setProduct(newProduct);
            solicitationRequestDAO.save(solicitationRequest);
        }
    }

    public User getUserLogged(){
        User user = (User) httpSession.getAttribute("userLogged");
        return  user;
    }

    public void updateStatusFinalize(Quotation quotation){
        quotation = quotationDAO.findById(Quotation.class,quotation.getId());
        quotation.setStatus(StatusEnum.Finished);
        quotation.setFinalDate(new Timestamp(new Date().getTime()));
        quotationDAO.save(quotation);
    }

    public void updateStatusSituationSolicitation(Solicitation solicitation){
        Integer totalAdd = getTotalAddQuotation(solicitation);

        if (totalAdd == 0){
            solicitation = solicitationDAO.findById(Solicitation.class,solicitation.getId());
            Situation situation = solicitation.getSituation();
            situation.setStatus(StatusEnum.Approved);
        } if (totalAdd > 0) {
            solicitation = solicitationDAO.findById(Solicitation.class,solicitation.getId());
            Situation situation = solicitation.getSituation();
            situation.setStatus(StatusEnum.InAnalysis);
        }
    }

    public Integer getTotalAddQuotation(Solicitation solicitation){
        return solicitationRequestDAO.totalSolicitationRequestAddQuotationBySolicitation(solicitation);
    }

    public Integer getTotalNotAddQuotation(Solicitation solicitation){
        return solicitationRequestDAO.totalSolicitationRequestNotAddQuotationBySolicitation(solicitation);
    }

    public Integer getTotalSolicitationRequest(Solicitation solicitation){
        return solicitationRequestDAO.totalSolicitationRequestBySolicitation(solicitation);
    }

}

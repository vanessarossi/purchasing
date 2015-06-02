package com.purchasing.service;

import com.purchasing.dao.*;
import com.purchasing.entity.*;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.printer.SolicitationPrinter;
import com.purchasing.service.impl.SolicitationService;
import com.purchasing.support.date.Conversor;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vanessa
 */
public class SolicitationServiceImpl implements SolicitationService {

    @Inject private HttpSession httpSession;
    @Inject private SolicitationPrinter solicitationPrinter;
    @Inject private SituationDAO situationDAO;
    @Inject private SolicitationDAO solicitationDAO;
    @Inject private SolicitationRequestDAO solicitationRequestDAO;
    @Inject private ServiceDAO serviceDAO;
    @Inject private CostCenterDAO costCenterDAO;
    @Inject private PurchaseOrderDAO purchaseOrderDAO;
    @Inject private QuotationDAO quotationDAO;

    @Override
    public Solicitation save(Solicitation solicitation) {
        solicitation.setUser(getUserLogged());
        solicitation.setInitialDate(new Timestamp(new Date().getTime()));
        Situation situation = new Situation();

        if (getUserLogged().getRole().getId() <= 7  ){
            situation.setStatus(StatusEnum.Approved);
            situation.setDateApproval(new Timestamp(new Date().getTime()));
        }else {
            situation.setStatus(StatusEnum.WaitingApproval);
        }

        Situation situationSaved = situationDAO.save(situation);
        solicitation.setSituation(situationSaved);
        Solicitation solicitationSaved = solicitationDAO.save(solicitation);

        if (solicitation.getType().equals(TypeEnum.Material)){
            List<SolicitationRequest>solicitationRequests = solicitation.getSolicitationRequests();
            for (SolicitationRequest solicitationRequest : solicitationRequests){
                SolicitationRequest newSolicitationRequest = new SolicitationRequest();
                newSolicitationRequest.setId(solicitationRequest.getId());
                newSolicitationRequest.setQuantity(solicitationRequest.getQuantity());
                newSolicitationRequest.setProduct(solicitationRequest.getProduct());
                newSolicitationRequest.setAddQuotation(false);
                newSolicitationRequest.setSolicitation(solicitationSaved);
                solicitationRequestDAO.save(newSolicitationRequest);
            }
        }else if (solicitation.getType().equals(TypeEnum.Service)){
            List<SolicitationRequest>solicitationRequests = solicitation.getSolicitationRequests();
            SolicitationRequest newSolicitationRequest = solicitationRequests.get(0);
            Service serviceSaved = serviceDAO.save(newSolicitationRequest.getService());

            newSolicitationRequest.setId(solicitationRequests.get(0).getId());
            newSolicitationRequest.setService(serviceSaved);
            newSolicitationRequest.setAddQuotation(false);
            newSolicitationRequest.setSolicitation(solicitationSaved);
            solicitationRequestDAO.save(newSolicitationRequest);

        }else if (solicitation.getType().equals(TypeEnum.MaterialService)){
            List<SolicitationRequest>solicitationRequests = solicitation.getSolicitationRequests();
            for (SolicitationRequest solicitationRequest : solicitationRequests){
                SolicitationRequest newSolicitationRequest = new SolicitationRequest();
                if (solicitationRequest.getService() != null){
                    Service serviceSaved = serviceDAO.save(solicitationRequest.getService());

                    newSolicitationRequest.setId(solicitationRequest.getId());
                    newSolicitationRequest.setService(serviceSaved);
                    newSolicitationRequest.setAddQuotation(false);
                    newSolicitationRequest.setSolicitation(solicitationSaved);
                    solicitationRequestDAO.save(newSolicitationRequest);

                }else{
                    newSolicitationRequest.setId(solicitationRequest.getId());
                    newSolicitationRequest.setQuantity(solicitationRequest.getQuantity());
                    newSolicitationRequest.setProduct(solicitationRequest.getProduct());
                    newSolicitationRequest.setAddQuotation(false);
                    newSolicitationRequest.setSolicitation(solicitationSaved);
                    solicitationRequestDAO.save(newSolicitationRequest);
                }
            }
        }
        return solicitationSaved;
    }

    @Override
    public Solicitation copy(Solicitation solicitation) {
        solicitation = solicitationDAO.findById(Solicitation.class,solicitation.getId());
        Solicitation copeSolicitation = new Solicitation();

        copeSolicitation.setCostCenter(solicitation.getCostCenter());
        copeSolicitation.setType(solicitation.getType());
        copeSolicitation.setUrgency(solicitation.getUrgency());
        copeSolicitation.setEmergency(solicitation.getEmergency());
        copeSolicitation.setObservation(solicitation.getObservation());

        List<SolicitationRequest>solicitationRequests = new ArrayList<>();
        for (SolicitationRequest solicitationRequest : solicitation.getSolicitationRequests()){
            SolicitationRequest newSolicitationRequest = new SolicitationRequest();
            if ((copeSolicitation.getType().equals(TypeEnum.Service) || copeSolicitation.getType().equals(TypeEnum.MaterialService)) && solicitationRequest.getService() != null){
                Service service = new Service();
                service.setDescription(solicitationRequest.getService().getDescription());
                service.setTypeService(solicitationRequest.getService().getTypeService());
                newSolicitationRequest.setService(service);
                newSolicitationRequest.setId(null);
                solicitationRequests.add(newSolicitationRequest);
            }else{
                newSolicitationRequest.setId(null);
                newSolicitationRequest.setProduct(solicitationRequest.getProduct());
                newSolicitationRequest.setQuantity(solicitationRequest.getQuantity());
                solicitationRequests.add(newSolicitationRequest);
            }
        }
        copeSolicitation.setSolicitationRequests(solicitationRequests);
        return copeSolicitation;
    }

    @Override
    public Solicitation searchById(Solicitation solicitation) {
        return solicitationDAO.findById(Solicitation.class,solicitation.getId());
    }

    @Override
    public File generateFile(Solicitation solicitation) {
        return solicitationPrinter.generate(solicitation.getId());
    }

    @Override
    public void removeSolicitationRequest(SolicitationRequest solicitationRequest) {
        solicitationRequestDAO.delete(solicitationRequest);
    }

    @Override
    public List<Object[]> findPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Solicitation> solicitations = solicitationDAO.pagination(search,iDisplayStart,iDisplayLength);
        List<Object[]> solicitationsList = new ArrayList<>();

        for (Solicitation solicitation : solicitations){
            String colCode = solicitation.getId().toString() ;
            String colInitialDate = Conversor.converterDateTimeInString(solicitation.getInitialDate());
            String colCostCenter = solicitation.getCostCenter().getDescription();
            String colName = solicitation.getUser().getName();
            String colStatus = solicitation.getSituation().getStatus().getDescription();
            String buttonView = "<a href=/purchasing/solicitacao/visualizar/"+solicitation.getId()+"/geral><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";
            String buttonPrinter= "<a href=/purchasing/solicitacao/imprimir/"+solicitation.getId()+"  target='_blank'><span class=\"fa fa-print btn btn-default btn-xs\"></span></a>";
            String buttonCancel = "<a  onclick=reproveSolicitation("+solicitation.getId()+")><span class=\"fa fa-times btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colCode,
                    colInitialDate,
                    colCostCenter,
                    colName,
                    colStatus,
                    buttonView,
                    buttonPrinter,
                    buttonCancel
            };
            solicitationsList.add(row);
        }
        return solicitationsList;
    }

    @Override
    public Integer totalPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        return solicitationDAO.totalPagination(search);
    }

    @Override
    public List<Object[]> findIndividualPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Solicitation> solicitations = new ArrayList<>();
        if (getUserLogged().getRole().getId() <= 7 && getUserLogged().getRole().getId()  != 1){
            solicitations = solicitationDAO.paginationIndividualCoordinator(search, iDisplayStart, iDisplayLength, getUserLogged().getCostCenters());
        }else{
            solicitations = solicitationDAO.paginationIndividual(search, iDisplayStart, iDisplayLength, getUserLogged());
        }

        List<Object[]> solicitationsList = new ArrayList<>();
        for (Solicitation solicitation : solicitations){
            String colCode = solicitation.getId().toString() ;
            String colInitialDate = Conversor.converterDateTimeInString(solicitation.getInitialDate());
            String colCostCenter = solicitation.getCostCenter().getDescription();
            String colName = solicitation.getUser().getName();
            String colStatus = solicitation.getSituation().getStatus().getDescription();
            String buttonView = "<a href=/purchasing/solicitacao/visualizar/"+solicitation.getId()+"/individual><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";
            String buttonEdit = "<a href=/purchasing/solicitacao/editar/"+solicitation.getId()+"><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String buttonCope = "<a href=/purchasing/solicitacao/copiar/"+solicitation.getId()+"><span class=\"fa fa-files-o btn btn-default btn-xs\"></span></a>";
            String buttonCancel = "<a href='#' onclick=cancelSolicitation("+solicitation.getId()+")><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colCode,
                    colInitialDate,
                    colCostCenter,
                    colName,
                    colStatus,
                    buttonView,
                    buttonEdit,
                    buttonCope,
                    buttonCancel
            };
            solicitationsList.add(row);
        }
        return solicitationsList;
    }

    @Override
    public Integer totalIndividualPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer quantity = 0;
        if (getUserLogged().getRole().getId() <= 7 && getUserLogged().getRole().getId()  != 1){
            quantity = solicitationDAO.totalIndividualPaginationCoordinator(search, getUserLogged().getCostCenters());
        }else{
            quantity = solicitationDAO.totalIndividualPagination(search, getUserLogged());
        }
        return quantity;
    }

    @Override
    public List<Object[]> findMissingPagination(String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Solicitation> solicitations = new ArrayList<>();
        if (getUserLogged().getRole().getId() == 5 || getUserLogged().getRole().getId() == 7 && getUserLogged().getRole().getId() != 1 ){
            solicitations = solicitationDAO.paginationMissingAnalyst(search, iDisplayStart, iDisplayLength, getUserLogged().getCostCenters());
        }else if (getUserLogged().getRole().getId() <= 6 && getUserLogged().getRole().getId() != 5 && getUserLogged().getRole().getId() != 1){
            solicitations = solicitationDAO.paginationMissingCoordinator(search, iDisplayStart, iDisplayLength, getUserLogged().getCostCenters());
        }

        List<Object[]> solicitationsList = new ArrayList<>();
        for (Solicitation solicitation : solicitations){
            String colCode = solicitation.getId().toString() ;
            String colInitialDate = Conversor.converterDateTimeInString(solicitation.getInitialDate());
            String colCostCenter = solicitation.getCostCenter().getDescription();
            String colName = solicitation.getUser().getName();
            String colStatus = solicitation.getSituation().getStatus().getDescription();
            String buttonView = "<a href=/purchasing/solicitacao/visualizar/"+solicitation.getId()+"/pendente><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colCode,
                    colInitialDate,
                    colCostCenter,
                    colName,
                    colStatus,
                    buttonView
            };
            solicitationsList.add(row);
        }
        return solicitationsList;
    }

    @Override
    public Integer totalMissingPagination(String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        Integer quantity = 0;
        if (getUserLogged().getRole().getId() == 5 || getUserLogged().getRole().getId() == 7 && getUserLogged().getRole().getId() != 1 ){
            quantity = solicitationDAO.totalMissingPaginationAnalyst(search, getUserLogged().getCostCenters());
        }else if (getUserLogged().getRole().getId() <= 6 && getUserLogged().getRole().getId() != 5 && getUserLogged().getRole().getId() != 1){
            quantity = solicitationDAO.totalMissingPaginationCoordinator(search, getUserLogged().getCostCenters());
        }
        return quantity;
    }

    @Override
    public List<Object[]> findPaginationSolicitationRequests(Solicitation solicitation, String sSearch, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<SolicitationRequest> solicitationRequests = solicitationRequestDAO.paginationBySolicitation(solicitation, search, iDisplayStart, iDisplayLength);
        List<Object[]> solicitationRequestsList = new ArrayList<>();

        for (SolicitationRequest solicitationRequest : solicitationRequests){
            String description = solicitationRequest.getProduct().getDescription();
            String model = solicitationRequest.getProduct().getModel() == null ? " " : solicitationRequest.getProduct().getModel();
            String mark = solicitationRequest.getProduct().getMark() == null ? " " : solicitationRequest.getProduct().getMark();

            String colProductCode =  solicitationRequest.getProduct().getId().toString();
            String colProduct = description +" "+model+" "+mark;
            String colQuantity = solicitationRequest.getQuantity().toString().replace(".",",");
            String colUnit = solicitationRequest.getProduct().getUnit().getDescription();
            String colStatus = solicitationRequest.getStatus() == null ? " " : solicitationRequest.getStatus().getDescription();

            String [] row = {
                    colProductCode,
                    colProduct,
                    colQuantity,
                    colUnit,
                    colStatus
            };
            solicitationRequestsList.add(row);
        }
        return solicitationRequestsList;
    }

    @Override
    public Integer totalPaginationSolicitationRequests(Solicitation solicitation, String sSearch) {
        String search = sSearch == null ? "" : sSearch;
        return solicitationRequestDAO.totalPaginationBySolicitation(solicitation,search);
    }

    @Override
    public List<Object[]> findPaginationWithFilter(String sSearch, StatusEnum status, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Solicitation> solicitations = solicitationDAO.paginationWithFilter(search, status ,iDisplayStart,iDisplayLength);
        List<Object[]> solicitationsList = new ArrayList<>();

        for (Solicitation solicitation : solicitations){
            String colCode = solicitation.getId().toString() ;
            String colInitialDate = Conversor.converterDateTimeInString(solicitation.getInitialDate());
            String colCostCenter = solicitation.getCostCenter().getDescription();
            String colName = solicitation.getUser().getName();
            String colStatus = solicitation.getSituation().getStatus().getDescription();
            String buttonView = "<a href=/purchasing/solicitacao/visualizar/"+solicitation.getId()+"/geral><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";
            String buttonPrinter= "<a href=/purchasing/solicitacao/imprimir/"+solicitation.getId()+"  target='_blank'><span class=\"fa fa-print btn btn-default btn-xs\"></span></a>";
            String buttonCancel = "<a  onclick=reproveSolicitation("+solicitation.getId()+")><span class=\"fa fa-times btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colCode,
                    colInitialDate,
                    colCostCenter,
                    colName,
                    colStatus,
                    buttonView,
                    buttonPrinter,
                    buttonCancel
            };
            solicitationsList.add(row);
        }
        return solicitationsList;
    }

    @Override
    public Integer totalPaginationWithFilter(String sSearch, StatusEnum status) {
        String search = sSearch == null ? "" : sSearch;
        return solicitationDAO.totalPaginationWithFilter(search,status);
    }

    @Override
    public List<Object[]> findIndividualPaginationWithFilter(String sSearch, StatusEnum status, int iDisplayStart, int iDisplayLength) {
        String search = sSearch == null ? "" : sSearch;
        List<Solicitation> solicitations = new ArrayList<>();
        if (getUserLogged().getRole().getId() <= 7){
            solicitations = solicitationDAO.paginationIndividualCoordinatorWithFilter(search,status ,iDisplayStart, iDisplayLength, getUserLogged().getCostCenters());
        }else{
            solicitations = solicitationDAO.paginationIndividualWithFilter(search, status,iDisplayStart, iDisplayLength, getUserLogged());
        }

        List<Object[]> solicitationsList = new ArrayList<>();
        for (Solicitation solicitation : solicitations){
            String colCode = solicitation.getId().toString() ;
            String colInitialDate = Conversor.converterDateTimeInString(solicitation.getInitialDate());
            String colCostCenter = solicitation.getCostCenter().getDescription();
            String colName = solicitation.getUser().getName();
            String colStatus = solicitation.getSituation().getStatus().getDescription();
            String buttonView = "<a href=/purchasing/solicitacao/visualizar/"+solicitation.getId()+"/individual><span class=\"fa fa-eye btn btn-default btn-xs\"></span></a>";
            String buttonEdit = "<a href=/purchasing/solicitacao/editar/"+solicitation.getId()+"><span class=\"fa fa-edit btn btn-default btn-xs\"></span></a>";
            String buttonCope = "<a href=/purchasing/solicitacao/copiar/"+solicitation.getId()+"><span class=\"fa fa-files-o btn btn-default btn-xs\"></span></a>";
            String buttonCancel = "<a href='#' onclick=cancelSolicitation("+solicitation.getId()+")><span class=\"fa fa-trash-o btn btn-default btn-xs\"></span></a>";
            String [] row = {
                    colCode,
                    colInitialDate,
                    colCostCenter,
                    colName,
                    colStatus,
                    buttonView,
                    buttonEdit,
                    buttonCope,
                    buttonCancel
            };
            solicitationsList.add(row);
        }
        return solicitationsList;
    }

    @Override
    public Integer totalIndividualPaginationWithFilter(String sSearch, StatusEnum status) {
        String search = sSearch == null ? "" : sSearch;
        Integer quantity = 0;
        if (getUserLogged().getRole().getId() <= 7){
            quantity = solicitationDAO.totalIndividualPaginationCoordinatorWithFilter(search,status, getUserLogged().getCostCenters());
        }else{
            quantity = solicitationDAO.totalIndividualPaginationWithFilter(search,status ,getUserLogged());
        }
        return quantity;
    }

    @Override
    public void approval(Solicitation solicitation) {
        solicitation = solicitationDAO.findById(Solicitation.class,solicitation.getId());
        solicitation.getSituation().setDateApproval(new Timestamp(new Date().getTime()));
        alterStatus(solicitation, StatusEnum.Approved);
    }

    @Override
    public void reject(Solicitation solicitation) {
        String justificationDisapproval = solicitation.getSituation().getJustificationDisapproval();
        solicitation = solicitationDAO.findById(Solicitation.class,solicitation.getId());
        solicitation.getSituation().setJustificationDisapproval(justificationDisapproval);
        solicitation.setFinalDate(new Timestamp(new Date().getTime()));
        alterStatus(solicitation, StatusEnum.Reject);
    }

    @Override
    public void preAnalysisReject(Solicitation solicitation) {
        String justificationDisapproval = solicitation.getSituation().getJustificationDisapproval();
        solicitation = solicitationDAO.findById(Solicitation.class,solicitation.getId());
        if (solicitation.getSituation().getStatus() == StatusEnum.Approved){
            solicitation.getSituation().setJustificationDisapproval(justificationDisapproval);
            solicitation.setFinalDate(new Timestamp(new Date().getTime()));
            alterStatus(solicitation, StatusEnum.PreAnalysisReject);
        }
    }

    @Override
    public void cancellationRequest(Solicitation solicitation) {
        String justificationCancelation = solicitation.getSituation().getJustificationCancellation();
        solicitation = solicitationDAO.findById(Solicitation.class,solicitation.getId());
        if (getUserLogged().getId() == solicitation.getUser().getId() &&  solicitation.getSituation().getStatus().ordinal() <= 2){
            solicitation.getSituation().setJustificationCancellation(justificationCancelation);
            solicitation.setFinalDate(new Timestamp(new Date().getTime()));
            alterStatus(solicitation,StatusEnum.CancellationRequest);
        }
    }

    @Override
    public void alterStatus(Solicitation solicitation, StatusEnum statusEnum) {
        solicitation = solicitationDAO.findById(Solicitation.class,solicitation.getId());
        Situation situation = solicitation.getSituation();
        situation.setStatus(statusEnum);
        situationDAO.save(situation);
        solicitationDAO.save(solicitation);
    }

    @Override
    public void cancel(Solicitation solicitation) {
        solicitation = solicitationDAO.findById(Solicitation.class,solicitation.getId());
        if (solicitation.getSituation().getStatus().equals(StatusEnum.CancellationRequest)){
            solicitation.setFinalDate(new Timestamp(new Date().getTime()));
            alterStatus(solicitation,StatusEnum.Canceled);
        }
    }

    @Override
    public void finalize(Solicitation solicitation) {
        List<SolicitationRequest> solicitationRequests = new ArrayList<>();
        solicitationRequests = solicitation.getSolicitationRequests();

        for (SolicitationRequest solicitationRequest : solicitationRequests){
            SolicitationRequest solicitationRequestFound = solicitationRequestDAO.findById(SolicitationRequest.class, solicitationRequest.getId());
            solicitationRequestFound.setDeliveryDate(new Timestamp(new Date().getTime()));
            solicitationRequestFound.setStatus(solicitationRequest.getStatus());
            solicitationRequestDAO.save(solicitationRequestFound);
        }

        Solicitation solicitationFound = solicitationDAO.findById(Solicitation.class,solicitation.getId());

        Integer total = solicitationFound.getSolicitationRequests().size();
        Integer totalDelivered = solicitationRequestDAO.findSolicitationRequestDelivered(solicitation).size();
        Integer remainder = total - totalDelivered;

        if (total == totalDelivered){
            Situation situation = solicitationFound.getSituation();
            situation.setStatus(StatusEnum.Finished);
            solicitationFound.setFinalDate(new Timestamp(new Date().getTime()));
            situationDAO.save(situation);
            solicitationDAO.save(solicitationFound);
        }
        if (total == remainder){
            Situation situation = solicitationFound.getSituation();
            situation.setStatus(null);
            solicitationFound.setFinalDate(null);
            situationDAO.save(situation);
            solicitationDAO.save(solicitationFound);
        }
        if ((totalDelivered > 0) && (total != totalDelivered)){
            Situation situation = solicitationFound.getSituation();
            situation.setStatus(StatusEnum.PartiallyFinished);
            solicitationFound.setFinalDate(null);
            situationDAO.save(situation);
            solicitationDAO.save(solicitationFound);
        }
    }

    @Override
    public List<SolicitationRequest> searchSolicitationRequestProductByProduct(Product product) {
        List<SolicitationRequest>  solicitationRequests  = new ArrayList<>();
        solicitationRequests = solicitationRequestDAO.findSolicitationRequestProductByProduct(product);
        return solicitationRequests;

    }

    @Override
    public List<SolicitationRequest> searchSolicitationRequestProductBySolicitation(Solicitation solicitation) {
        List<SolicitationRequest>  solicitationRequests  = new ArrayList<>();
        solicitationRequests = solicitationRequestDAO.findSolicitationRequestProductBySolicitation(solicitation);
        return solicitationRequests;
    }

    @Override
    public SolicitationRequest searchSolicitationRequestServiceBySolicitation(Solicitation solicitation) {
        SolicitationRequest solicitationRequest = new SolicitationRequest();
        solicitationRequest = solicitationRequestDAO.findSolicitationRequestServiceBySolicitation(solicitation);
        return solicitationRequest;
    }

    @Override
    public Solicitation findByApprovedPartiallyDelivered(Solicitation solicitation) {
        return solicitationDAO.findByApprovedPartiallyDelivered(solicitation);
    }

    @Override
    public List<PurchaseOrder> getPurchaseOrdersWithJustification(Solicitation solicitation) {
        return purchaseOrderDAO.findPurchaseWithJustification(solicitation);
    }

    @Override
    public List<Quotation> getQuotationCancelledWithJustification(Solicitation solicitation) {
        return quotationDAO.getAllCancelledBySolicitation(solicitation);
    }

    @Override
    public User getUserLogged(){
        User user = (User) httpSession.getAttribute("userLogged");
        return  user;
    }
}

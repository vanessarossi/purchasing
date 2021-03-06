package com.purchasing.controller;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.access.EighthLevelAccessRule;
import com.purchasing.access.FifthLevelAccessRule;
import com.purchasing.access.FirstLevelAccessRule;
import com.purchasing.access.SeventhLevelAccessRule;
import com.purchasing.entity.Product;
import com.purchasing.entity.Solicitation;
import com.purchasing.entity.SolicitationRequest;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.service.impl.SolicitationService;
import com.purchasing.service.impl.TypeServiceService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/solicitacao")
public class SolicitationController {

    private Result result;
    private Validator validator;
    private SolicitationService solicitationService;
    private TypeServiceService typeServiceService;

    @Deprecated SolicitationController(){};

    @Inject
    public SolicitationController(Result result, Validator validator, SolicitationService solicitationService, TypeServiceService typeServiceService){
        this.result = result;
        this.validator = validator;
        this.solicitationService = solicitationService;
        this.typeServiceService = typeServiceService;
    }

    @Post("/salvar")
    public void save(Solicitation solicitation , SolicitationRequest solicitationRequest){
        validator.addIf(solicitation.getType().equals(TypeEnum.Material) && solicitation.getSolicitationRequests() == null, new I18nMessage("message.error.solicitiation.material", "message.error.solicitiation.material"));
        validator.addIf(solicitation.getType().equals(TypeEnum.Service) && solicitationRequest.getService().getDescription() == null, new I18nMessage("message.error.solicitiation.service", "message.error.solicitiation.service"));
        validator.addIf(solicitation.getType().equals(TypeEnum.MaterialService) && (solicitationRequest.getService().getDescription() == null || solicitation.getSolicitationRequests() == null) , new I18nMessage("message.error.solicitiation.service.material","message.error.solicitiation.service.material"));
        validator.addIf(solicitation.getCostCenter() == null, new I18nMessage("message.error.no.costCenter","message.error.no.costCenter"));
        validator.onErrorForwardTo(this).form();

        if (solicitation.getType() !=  TypeEnum.Material){
            List<SolicitationRequest> solicitationRequests;
            if (solicitation.getSolicitationRequests() == null){
                solicitationRequests = new ArrayList<>();
            }else{
                solicitationRequests = solicitation.getSolicitationRequests();
            }
            solicitationRequests.add(solicitationRequest);
            solicitation.setSolicitationRequests(solicitationRequests);
        }
        solicitationService.save(solicitation);
        result.redirectTo(this).individualList();
    }

    @Get("/editar/{solicitation.id}")
    public void edit(Solicitation solicitation){
        Solicitation solicitationFound = solicitationService.searchById(solicitation);
        if (solicitationFound.getUser().getId().equals(solicitationService.getUserLogged().getId()) && solicitationFound.getSituation().getStatus().ordinal() <= 2 ){
            if (solicitationFound.getType()!= TypeEnum.Material){
               for (SolicitationRequest solicitationRequest : solicitationFound.getSolicitationRequests()){
                   if (solicitationRequest.getService() != null){
                       result.include("solicitationRequest",solicitationRequest);
                   }
               }
            }
            result.include("solicitation",solicitationFound);
            result.redirectTo(this).form();
        }else{
            result.include("messageErrorEdit","message.error.edit.solicitation");
            result.redirectTo(this).individualList();
        }
    }

    @Get("/copiar/{solicitation.id}")
    public void copy(Solicitation solicitation){
        Solicitation solicitationFound = solicitationService.copy(solicitation);
            if (solicitationFound.getType()!= TypeEnum.Material){
               for (SolicitationRequest solicitationRequest : solicitationFound.getSolicitationRequests()){
                   if (solicitationRequest.getService() != null){
                       result.include("solicitationRequest",solicitationRequest);
                   }
               }
            }
            result.include("solicitation", solicitationFound);
            result.redirectTo(this).form();
    }

    @Get("/visualizar/{solicitation.id}/{type}")
    public void visualize(Solicitation solicitation,String type) {
        solicitation = solicitationService.searchById(solicitation);
        if (solicitation.getType()!= TypeEnum.Material){
            for (SolicitationRequest solicitationRequest : solicitation.getSolicitationRequests()){
                if (solicitationRequest.getService() != null){
                    result.include("solicitationRequest",solicitationRequest);
                }
            }
        }
        if (solicitation.getSituation().getStatus() == StatusEnum.PurchaseOrderCanceled || solicitation.getSituation().getStatus() == StatusEnum.QuoteReject || solicitation.getSituation().getStatus() == StatusEnum.PartiallyQuoteApproved){
            result.include("purchaseOrders",solicitationService.getPurchaseOrdersWithJustification(solicitation));
        }else if (solicitation.getSituation().getStatus() == StatusEnum.QuotationCanceled){
            result.include("quotations",solicitationService.getQuotationCancelledWithJustification(solicitation));
        }

        result.include("type",type);
        result.include("solicitation",solicitation);
        result.redirectTo(this).visualizeForm();
     }

    @Get("/visualizacao/rapida/{solicitation.id}/json")
    public void visualizeQick(Solicitation solicitation) {
        solicitation = solicitationService.searchById(solicitation);
        if (solicitation.getType() == TypeEnum.Material) {
            result.use(Results.json()).withoutRoot().from(solicitation).include("costCenter").include("user").include("solicitationRequests").include("solicitationRequests.product").serialize();
        }else if(solicitation.getType() == TypeEnum.Service) {
            result.use(Results.json()).withoutRoot().from(solicitation).include("costCenter").include("user").include("solicitationRequests").include("solicitationRequests.service").include("solicitationRequests.service.typeService").serialize();
        }else if(solicitation.getType() == TypeEnum.MaterialService){
            result.use(Results.json()).withoutRoot().from(solicitation).include("costCenter").include("user").include("solicitationRequests").include("solicitationRequests.service").include("solicitationRequests.service.typeService").include("solicitationRequests.product").serialize();
        }
    }


    @Get("/remover/produto/{solicitationRequest.id}/json")
    public void removeProduct(SolicitationRequest solicitationRequest){
        solicitationService.removeSolicitationRequest(solicitationRequest);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Get("/imprimir/{solicitation.id}")
    public File printer(Solicitation solicitation){
        File file = solicitationService.generateFile(solicitation);
        return file;
    }


    /** Formulários **/
    @CustomBrutauthRules(FifthLevelAccessRule.class)
    @Path("/listar")
    public void list() {
        result.include("status", StatusEnum.getStatusSolicitation());
        result.include("controller", this.getClass().toString());
    }

    @CustomBrutauthRules(FirstLevelAccessRule.class)
    @Path("/listar/individual")
    public void individualList(){
        result.include("status", StatusEnum.getStatusSolicitation());
        result.include("controller", this.getClass().toString());
    }

    @CustomBrutauthRules(SeventhLevelAccessRule.class)
    @Path("/listar/pendente")
    public void missingList() {
           result.include("controller", this.getClass().toString());
       }

    @CustomBrutauthRules(FirstLevelAccessRule.class)
    @Path("/formulario")
    public void form() {
            result.include("typesService",typeServiceService.findAll());
            result.include("user",solicitationService.getUserLogged());
            result.include("types", TypeEnum.values());
            result.include("controller", this.getClass().toString());
        }

    @CustomBrutauthRules(FirstLevelAccessRule.class)
    @Path("/visulizar")
    public void visualizeForm() {
            result.include("controller", this.getClass().toString());
        }

    @CustomBrutauthRules(EighthLevelAccessRule.class)
    @Path("/formulario/finalizacao")
    public void finalizationForm(){
        result.include("controller", this.getClass().toString());
    }

    /** Paginação **/
    @Get("/paginar/filtro/{status}")
    public void paginationWithFilter(String sSearch,StatusEnum status,String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> solicitationObjects = solicitationService.findPaginationWithFilter(sSearch, status,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(solicitationService.totalPaginationWithFilter(sSearch,status));
            dataTableModel.setiTotalDisplayRecords(solicitationService.totalPaginationWithFilter(sSearch,status));
            dataTableModel.setAaData(solicitationObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/paginar/individual/filtro/{status}")
    public void individualPaginationWithFilter(String sSearch,StatusEnum status ,String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> solicitationObjects = solicitationService.findIndividualPaginationWithFilter(sSearch, status,iDisplayStart, iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(solicitationService.totalIndividualPaginationWithFilter(sSearch,status));
            dataTableModel.setiTotalDisplayRecords(solicitationService.totalIndividualPaginationWithFilter(sSearch,status));
            dataTableModel.setAaData(solicitationObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> solicitationObjects = solicitationService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(solicitationService.totalPagination(sSearch));
        dataTableModel.setiTotalDisplayRecords(solicitationService.totalPagination(sSearch));
        dataTableModel.setAaData(solicitationObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/paginar/individual")
    public void individualPagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> solicitationObjects = solicitationService.findIndividualPagination(sSearch, iDisplayStart, iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(solicitationService.totalIndividualPagination(sSearch));
        dataTableModel.setiTotalDisplayRecords(solicitationService.totalIndividualPagination(sSearch));
        dataTableModel.setAaData(solicitationObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/paginar/pendencia")
    public void missingPagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> solicitationObjects = solicitationService.findMissingPagination(sSearch, iDisplayStart, iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(solicitationService.totalMissingPagination(sSearch));
            dataTableModel.setiTotalDisplayRecords(solicitationService.totalMissingPagination(sSearch));
            dataTableModel.setAaData(solicitationObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/paginar/produto/{solicitation.id}")
    public void paginationBySolicitation(Solicitation solicitation,String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> solicitationObjects = solicitationService.findPaginationSolicitationRequests(solicitation,sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(solicitationService.totalPaginationSolicitationRequests(solicitation,sSearch));
            dataTableModel.setiTotalDisplayRecords(solicitationService.totalPaginationSolicitationRequests(solicitation,sSearch));
            dataTableModel.setAaData(solicitationObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }


    /** Alteração de status **/
    @Post("/aprovar")
    public  void approve(Solicitation solicitation){
           solicitationService.approval(solicitation);
           result.redirectTo(this).missingList();
       }

    @Post("/cancelar/analise")
    public void preAnalysisReject(Solicitation solicitation){
           solicitationService.preAnalysisReject(solicitation);
           result.redirectTo(this).list();
       }

    @Post("/reprovar")
    public void reject(Solicitation solicitation){
           solicitationService.reject(solicitation);
           result.redirectTo(this).missingList();
       }

    @Post("/pedir/cancelamento")
    public void cancelationRequest(Solicitation solicitation){
           solicitationService.cancellationRequest(solicitation);
           result.redirectTo(this).individualList();
       }

    @Post("/confirmar/cancelamento")
    public void confirmCancelationRequest(Solicitation solicitation){
           solicitationService.cancel(solicitation);
           result.redirectTo(this).missingList();
       }

    @Post("/pesquisar/finalizacao")
    public void findSolicitationFinalization(Solicitation solicitation){
      solicitation =   solicitationService.findByApprovedPartiallyDelivered(solicitation);
        if (solicitation != null) {
            if (solicitation.getType() != TypeEnum.Material) {
                for (SolicitationRequest solicitationRequest : solicitation.getSolicitationRequests()) {
                    if (solicitationRequest.getService() != null) {
                        result.include("solicitationRequest", solicitationRequest);
                    }
                }
            }
            result.include("solicitation", solicitation);
        }else{
            result.include("messageNotSolicitation", "message.not.solicitation");
        }
        result.redirectTo(this).finalizationForm();
    }

    @Post("finalizar")
    public void finalize(Solicitation solicitation,SolicitationRequest solicitationRequest){
        List<SolicitationRequest>solicitationRequests = new ArrayList<>();
        if (solicitationRequest.getId() != null && solicitation.getSolicitationRequests() == null){
            solicitationRequests.add(solicitationRequest);
        }if (solicitationRequest.getId() == null && solicitation.getSolicitationRequests() != null){
            solicitationRequests.addAll(solicitation.getSolicitationRequests());
        }if (solicitationRequest.getId() != null && solicitation.getSolicitationRequests() != null){
            solicitationRequests.add(solicitationRequest);
            solicitationRequests.addAll(solicitation.getSolicitationRequests());
        }
        solicitation.setSolicitationRequests(solicitationRequests);
        solicitationService.finalize(solicitation);
        result.redirectTo(this).finalizationForm();
    }

    /** Pesquisa de pedido de solicitação json  **/

    @Get("/pesquisar/pedido/produto/{product.id}/json")
    public void searchSolicitationRequestProductByProduct(Product product){
        List<SolicitationRequest> solicitationRequests = solicitationService.searchSolicitationRequestProductByProduct(product);
        if (solicitationRequests != null){
            result.use(Results.json()).withoutRoot().from(solicitationRequests).include("solicitation").include("solicitation.costCenter").include("product").include("product.unit").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }

    @Get("/pesquisar/pedido/solicitacao/{solicitation.id}/json")
    public void searchSolicitationRequestProductBySolicitationId(Solicitation solicitation){
        List<SolicitationRequest> solicitationRequests = solicitationService.searchSolicitationRequestProductBySolicitation(solicitation);
        if (solicitationRequests != null){
            result.use(Results.json()).withoutRoot().from(solicitationRequests).include("solicitation").include("solicitation.costCenter").include("product").include("product.unit").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }

    @Get("/pesquisar/pedido/servico/solicitacao/{solicitation.id}/json")
    public void searchSolicitationRequestServiceBySolicitationId(Solicitation solicitation){
        SolicitationRequest solicitationRequest = solicitationService.searchSolicitationRequestServiceBySolicitation(solicitation);
        if (solicitationRequest != null){
            result.use(Results.json()).withoutRoot().from(solicitationRequest).include("solicitation").include("solicitation.costCenter").include("service").include("service.typeService").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }


}

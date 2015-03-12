package com.purchasing.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.entity.*;
import com.purchasing.enumerator.MeanPaymentEnum;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.service.impl.BudgetService;
import com.purchasing.service.impl.FormPaymentService;
import com.purchasing.service.impl.QuotationService;
import com.purchasing.support.datatable.DataTableModel;
import com.purchasing.support.quotation.QuotationRequestProductView;
import com.purchasing.support.quotation.QuotationRequestServiceView;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */

@Controller
@Path("/cotacao")
public class QuotationController {

    private Result result;
    private QuotationService quotationService;
    private FormPaymentService formPaymentService;
    private BudgetService budgetService;
    private Validator validator;

    @Deprecated
    public QuotationController() {}

    @Inject
    public QuotationController(Result result, QuotationService quotationService, FormPaymentService formPaymentService, BudgetService budgetService, Validator validator) {
        this.result = result;
        this.quotationService = quotationService;
        this.formPaymentService = formPaymentService;
        this.budgetService = budgetService;
        this.validator = validator;
    }

    /** Ação **/
    @Post("/salvar")
    public void save(Quotation quotation) {
       Quotation quotationSaved =  quotationService.save(quotation);
        result.include("quotation",quotationSaved);
        result.redirectTo(this).formQuotation();
    }

    @Post("/salvar/pedido/material")
    public void saveRequestMaterial(Quotation quotation, List<SolicitationRequest> solicitationRequests) {
        quotationService.addQuotationRequestProduct(quotation,solicitationRequests);
        Quotation quotationSaved = quotationService.searchById(quotation);
        result.include("quotation",quotationSaved);
        result.redirectTo(this).formQuotation();
    }

    @Post("/salvar/pedido/servico")
    public void saveRequestService(Quotation quotation, SolicitationRequest solicitationRequest) {
        quotationService.addQuotationRequestService(quotation,solicitationRequest);
        Quotation quotationSaved = quotationService.searchById(quotation);
        result.include("quotation",quotationSaved);
        result.redirectTo(this).formQuotation();
    }

    @Post("/salvar/orcamento")
    public void saveBudget(Budget budget) {
        budget = budgetService.saveBudget(budget);
        Quotation quotation = budget.getQuotation();
        result.include("quotation",quotation);
        result.redirectTo(this).formQuotation();
    }

    @Get("/formulario/adicionar/{quotation.id}")
    public void addRequest(Quotation quotation) {
        quotation = quotationService.searchById(quotation);
        result.include("quotation",quotation);
        result.redirectTo(this).formQuotationAddRequest();
    }

    @Get("/formulario/adicionar/orcamento/{quotation.id}")
    public void addBudget(Quotation quotation) {
        quotation = quotationService.searchById(quotation);
        if (quotation.getType().equals(TypeEnum.Material)){
            List<QuotationRequestProductView> quotationRequests = quotationService.groupByProduct(quotation);
            result.include("quotationRequests",quotationRequests);
        }else if(quotation.getType().equals(TypeEnum.Service)){
            List<QuotationRequestServiceView> quotationRequests = new QuotationRequestServiceView().generateList(quotationService.searchQuotationRequestServiceByQuotation(quotation));
            result.include("quotationRequests",quotationRequests);
        }
        result.include("formsPayment",formPaymentService.findAll());
        result.include("meansPayment", MeanPaymentEnum.values());
        result.include("quotation",quotation);
        result.redirectTo(this).formBudget();
    }

    @Get("/formulario/visualizar/detalhes/{quotation.id}")
    public void viewDetails(Quotation quotation) {
        quotation = quotationService.searchById(quotation);
        if (quotation.getType().equals(TypeEnum.Material)){
            List<QuotationRequestProductView> quotationRequests = quotationService.groupByProduct(quotation);
            result.include("quotationRequests",quotationRequests);
        }else if(quotation.getType().equals(TypeEnum.Service)){
            List<QuotationRequestServiceView> quotationRequests = new QuotationRequestServiceView().generateList(quotationService.searchQuotationRequestServiceByQuotation(quotation));
            result.include("quotationRequests",quotationRequests);
        }
        result.include("quotation",quotation);
        result.redirectTo(this).formVisualize();
    }

    @Get("/formulario/iniciar/ordem/{quotation.id}")
    public void initialPurchaseOrder(Quotation quotation) {
        quotation = quotationService.searchById(quotation);
        if (quotation.getType().equals(TypeEnum.Material)){
            List<QuotationRequestProductView> quotationRequests = quotationService.groupByProduct(quotation);
            result.include("quotationRequests",quotationRequests);
        }else if(quotation.getType().equals(TypeEnum.Service)){
            List<QuotationRequestServiceView> quotationRequests = new QuotationRequestServiceView().generateList(quotationService.searchQuotationRequestServiceByQuotation(quotation));
            result.include("quotationRequests",quotationRequests);
        }
        result.include("quotation",quotation);
        result.redirectTo(this).formInitialPurchaseOrder();
    }

    @Get("/editar/{quotation.id}")
    public void edit(Quotation quotation) {
        quotation = quotationService.searchOpenById(quotation);
        if (quotation == null){
            result.include("errorQuotatioFinalized","message.error.quotation.finalized");
            result.redirectTo(this).list();
        }else{
            result.include("quotation",quotation);
            result.redirectTo(this).formQuotation();
        }
    }

    @Get("/pesquisar/{quotation.id}")
    public void searchById(Quotation quotation) {
        quotation = quotationService.searchById(quotation);
           result.include("quotation",quotation);
           result.redirectTo(this).formQuotation();
    }

    @Get("/deletar/pedido/material/total/{quotation.id}/{product.id}/json")
    public void removeQuotationRequestProductByProduct(Quotation quotation,Product product) {
        quotationService.removeQuotationRequestByProduct(quotation,product);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Get("/deletar/pedido/{quotationRequest.id}/json")
    public void removeQuotationRequestServiceById(QuotationRequest quotationRequest) {
        quotationService.removeQuotationRequest(quotationRequest);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }


    /** Formulários **/
    @Path("/")
    public void list() {
        result.include("controller", this.getClass().toString());
    }

    @Path("/formulario")
    public void formQuotation() {
        result.include("types", TypeEnum.findTypeQuotation());
        result.include("controller", this.getClass().toString());
    }

    @Path("/formulario/orcamento")
    public void formBudget() {
        result.include("controller", this.getClass().toString());
    }

    @Path("/detalhes")
    public void formVisualize() {
        result.include("controller", this.getClass().toString());
    }

    @Path("/formulario/finalizar")
    public void formInitialPurchaseOrder() {
        result.include("controller", this.getClass().toString());
    }

    @Path("/formulario/adicionar")
    public void formQuotationAddRequest() {
        result.include("controller", this.getClass().toString());
    }

    /** Paginação **/
    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> quotationObjects = quotationService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(quotationService.totalPagination(sSearch));
        dataTableModel.setiTotalDisplayRecords(quotationService.totalPagination(sSearch));
        dataTableModel.setAaData(quotationObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }


    /** Listagem total de produtos e serviços **/
    @Get("/listagem/total/material/{quotation.id}/json")
    public void listRequestMaterial(Quotation quotation) {
        List<QuotationRequestProductView> listGroupQuotationRequest = quotationService.groupByProduct(quotation);
        if (listGroupQuotationRequest != null){
            result.use(Results.json()).withoutRoot().from(listGroupQuotationRequest).include("product").include("product.unit").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }

    @Get("/listagem/total/servico/{quotation.id}/json")
    public void listRequestService(Quotation quotation) {
        List<QuotationRequestServiceView> quotationRequestsService = new QuotationRequestServiceView().generateList(quotationService.searchQuotationRequestServiceByQuotation(quotation));
        if (quotationRequestsService != null){
            result.use(Results.json()).withoutRoot().from(quotationRequestsService).include("solicitation").include("costCenter").include("typeService").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }

    @Get("/listagem/detalhada/material/{quotation.id}/{product.id}/json")
    public void listRequestMaterialDetail(Quotation quotation,Product product) {
        List<QuotationRequest> quotationRequests = quotationService.searchQuotationRequestProductByProduct(quotation,product);
        if (quotationRequests != null){
            result.use(Results.json()).withoutRoot().from(quotationRequests).include("solicitationRequest").include("solicitationRequest.solicitation").include("solicitationRequest.solicitation.costCenter").include("solicitationRequest.product").include("solicitationRequest.product.unit").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }

    /** Pesquisa  **/
    @Get("/pesquisar/detalhes/pagamento/{formPayment.id}/json")
    public void searchDetailFormPayment(FormPayment formPayment) {
        FormPayment formPaymentFound = formPaymentService.searchById(formPayment);
        if (formPaymentFound != null){
            result.use(Results.json()).withoutRoot().from(formPaymentFound).serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }
}

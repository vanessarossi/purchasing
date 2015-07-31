package com.purchasing.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.annotation.Admin;
import com.purchasing.annotation.Analyst;
import com.purchasing.annotation.Purchaser;
import com.purchasing.entity.*;
import com.purchasing.enumerator.MeanPaymentEnum;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.service.impl.BudgetService;
import com.purchasing.service.impl.FormPaymentService;
import com.purchasing.service.impl.QuotationService;
import com.purchasing.support.budget.BudgetQuotationProductView;
import com.purchasing.support.budget.BudgetQuotationServiceView;
import com.purchasing.support.budget.BudgetView;
import com.purchasing.support.datatable.DataTableModel;
import com.purchasing.support.quotation.QuotationRequestProductView;
import com.purchasing.support.quotation.QuotationRequestServiceView;

import javax.inject.Inject;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
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
        quotationService.addQuotationRequestService(quotation, solicitationRequest);
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

            Collections.sort(quotationRequests, new Comparator<QuotationRequestProductView>() {
                @Override
                public int compare(QuotationRequestProductView q1, QuotationRequestProductView q2) {
                    return q1.getProduct().getDescription().compareTo(q2.getProduct().getDescription());
                }
            });
            result.include("quotationRequests", quotationRequests);
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

            Collections.sort(quotationRequests, new Comparator<QuotationRequestProductView>() {
                @Override
                public int compare(QuotationRequestProductView o1, QuotationRequestProductView o2) {
                    return o1.getProduct().getDescription().compareTo(o2.getProduct().getDescription());
                }
            });


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
        List<BudgetView> budgets = new BudgetView().generateList(quotation.getBudgets(), budgetService);
        result.include("quotation",quotation);
        result.include("budgets",budgets);
        result.redirectTo(this).formInitialPurchaseOrder();
    }

    @Get("/editar/{quotation.id}")
    public void edit(Quotation quotation) {
        quotation = quotationService.searchById(quotation);

        if (quotation.getType().equals(TypeEnum.Material)){
            Collections.sort(quotation.getQuotationRequests(), new Comparator<QuotationRequest>() {
                @Override
                public int compare(QuotationRequest o1, QuotationRequest o2) {
                    return o1.getSolicitationRequest().getProduct().getDescription().compareTo(o2.getSolicitationRequest().getProduct().getDescription());
                }
            });
        }


        result.include("quotation",quotation);
        result.redirectTo(this).formQuotation();
    }

    @Get("/editar/orcamento/{budget.id}")
    public void editBudget(Budget budget) {
        budget = budgetService.findById(budget);
        if (budget.getQuotation().getType().equals(TypeEnum.Material)) {
            List<QuotationRequestProductView> quotationRequests = quotationService.groupByProduct(budget.getQuotation());
            List<BudgetQuotationProductView> budgetQuotations = budgetService.groupProductBudget(budget);
            result.include("quotationRequests", quotationRequests);
            result.include("budgetQuotations", budgetQuotations);
        } else if (budget.getQuotation().getType().equals(TypeEnum.Service)) {
            List<QuotationRequestServiceView> quotationRequests = new QuotationRequestServiceView().generateList(quotationService.searchQuotationRequestServiceByQuotation(budget.getQuotation()));
            List<BudgetQuotationServiceView> budgetQuotations = new BudgetQuotationServiceView().generateList(budget.getBudgetQuotations());
            result.include("quotationRequests", quotationRequests);
            result.include("budgetQuotations", budgetQuotations);
        }
        result.include("formsPayment", formPaymentService.findAll());
        result.include("meansPayment", MeanPaymentEnum.values());
        result.include("quotation",budget.getQuotation());
        result.include("budget", budget);
        result.redirectTo(this).formBudget();
    }

    @Get("/pesquisar/{quotation.id}")
    public void searchById(Quotation quotation) {
        quotation = quotationService.searchById(quotation);
           result.include("quotation",quotation);
           result.redirectTo(this).formQuotation();
    }

    @Get("/deletar/pedido/material/total/{quotation.id}/{product.id}/json")
    public void removeQuotationRequestProductByProduct(Quotation quotation,Product product) {
        quotationService.removeQuotationRequestByProduct(quotation, product);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Get("/deletar/pedido/{quotationRequest.id}/json")
    public void removeQuotationRequestServiceById(QuotationRequest quotationRequest) {
        quotationService.removeQuotationRequest(quotationRequest);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Post("/cancelar")
    public void cancellation(Quotation quotation){
        quotationService.saveCancellation(quotation);
        result.redirectTo(this).list();
    }


    /** Formulários **/

    @Purchaser
    @Analyst
    @Admin
    @Path("/")
    public void list() {
        result.include("status", StatusEnum.getStatusQuotation());
        result.include("controller", this.getClass().toString());
    }

    @Get("/imprimir/pedido/orcamento/{quotation.id}")
    public File printerOrderBudget(Quotation quotation){
        return quotationService.printer(quotation);
    }


    @Purchaser
    @Analyst
    @Admin
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

    @Get("/paginar/filtro/{status}")
    public void paginationWithFilter(String sSearch,StatusEnum status ,String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> quotationObjects = quotationService.findPaginationWithFilter(sSearch,status,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(quotationService.totalPaginationWithFilter(sSearch,status));
        dataTableModel.setiTotalDisplayRecords(quotationService.totalPaginationWithFilter(sSearch,status));
        dataTableModel.setAaData(quotationObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    /** Listagem total de produtos e serviços **/
    @Get("/listagem/total/material/{quotation.id}/json")
    public void listRequestMaterial(Quotation quotation) {
        List<QuotationRequestProductView> listGroupQuotationRequest = quotationService.groupByProduct(quotation);
        if (listGroupQuotationRequest != null){
            Collections.sort(listGroupQuotationRequest, new Comparator<QuotationRequestProductView>() {
                @Override
                public int compare(QuotationRequestProductView o1, QuotationRequestProductView o2) {
                    return o1.getProduct().getDescription().compareTo(o2.getProduct().getDescription());
                }
            });

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

    @Get("/pesquisa/produto/orcamento/{budget.id}/json")
    public void searchDetailBudgetQuotation(Budget budget){
        budget = budgetService.findById(budget);
        if (budget.getQuotation().getType().equals(TypeEnum.Material)) {
            List<BudgetQuotationProductView> budgetQuotations = budgetService.groupProductBudget(budget);

            Collections.sort(budgetQuotations, new Comparator<BudgetQuotationProductView>() {
                @Override
                public int compare(BudgetQuotationProductView o1, BudgetQuotationProductView o2) {
                    return o1.getProduct().getDescription().compareTo(o2.getProduct().getDescription());
                }
            });

            result.use(Results.json()).withoutRoot().from(budgetQuotations).include("product").include("product.unit").serialize();
        }else if (budget.getQuotation().getType().equals(TypeEnum.Service)) {
            List<BudgetQuotationServiceView> budgetQuotations = new BudgetQuotationServiceView().generateList(budget.getBudgetQuotations());
            result.use(Results.json()).withoutRoot().from(budgetQuotations).include("service").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize();
        }
    }
}

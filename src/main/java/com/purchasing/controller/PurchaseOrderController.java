package com.purchasing.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.entity.*;
import com.purchasing.enumerator.MeanPaymentEnum;
import com.purchasing.enumerator.StatusEnum;
import com.purchasing.enumerator.TypeEnum;
import com.purchasing.service.impl.FormPaymentService;
import com.purchasing.service.impl.PurchaseOrderService;
import com.purchasing.support.datatable.DataTableModel;
import com.purchasing.support.purchaseOrder.PurchaseOrderView;

import javax.inject.Inject;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/ordemCompra")
public class PurchaseOrderController {

    private Result result;
    private PurchaseOrderService purchaseOrderService;
    private FormPaymentService formPaymentService;

    @Deprecated
    public PurchaseOrderController() {
    }

    @Inject
    public PurchaseOrderController(Result result, PurchaseOrderService purchaseOrderService, FormPaymentService formPaymentService) {
        this.result = result;
        this.purchaseOrderService = purchaseOrderService;
        this.formPaymentService = formPaymentService;
    }

    /** Forms **/
    @Path("/listagem")
    public void list(){
        result.include("status", StatusEnum.getStatusPurchaseOrder());
        result.include("controller", this.getClass()).toString();
    }

    @Path("/listagem/pendencia")
    public void missingList(){
        result.include("controller", this.getClass()).toString();
    }

    @Path("/listagem/conferida")
    public void missingConferedList(){
        result.include("controller", this.getClass()).toString();
    }

    @Path("/formulario/recepcao")
    public void formReception(){
        result.include("controller", this.getClass()).toString();
    }

    @Path("/pesquisar")
    public void formSearch(){
        result.include("controller", this.getClass()).toString();
    }

    @Path("/formulario")
    public void form(){
        result.include("controller", this.getClass()).toString();
    }

    @Path("/visualizar")
    public void visualize() {
        result.include("controller", this.getClass()).toString();
    }

    @Path("/formulario/confirma/conferencia")
    public void formConfirmConference() {
        result.include("controller", this.getClass()).toString();
    }

     /** Actions **/
    @Get("/salvar/unico/{budget.id}")
    public void singleSave(Budget budget) {
        purchaseOrderService.singleSave(budget);
        result.redirectTo(this).list();
    }

    @Post("/salvar")
    public void variousSave(List<PurchaseOrder> purchaseOrders){
        purchaseOrderService.variousSave(purchaseOrders);
        result.redirectTo(this).list();
    }

    @Post("/salvar/justificado")
    public void saveWithJustification(Budget budget, String justification, Boolean exclusive) {
        purchaseOrderService.singleSaveWithJustification(budget, justification, exclusive);
        result.redirectTo(this).list();
    }

    @Get("/visualizar/{purchaseOrder.id}/{origin}")
    public void view(PurchaseOrder purchaseOrder, String origin){
        purchaseOrder = purchaseOrderService.findById(purchaseOrder);
        String approve = (origin.equals("approve")) ? "true" : "false";
        result.include("purchaseOrder",purchaseOrder);
        result.include("approve", approve);
        result.redirectTo(this).visualize();
    }

    @Post("/pesquisar/fornecedor")
    public void searchPurchaseOrderBySupplier(Supplier supplier){
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.findBySupplierOrderDate(supplier);
        result.include("purchaseOrders", purchaseOrders);
        result.redirectTo(this).formSearch();
    }

    @Post("/aprovar")
    public void approvePurchaseOrder(PurchaseOrder purchaseOrder, String observation){
        purchaseOrderService.approve(purchaseOrder,observation);
        result.redirectTo(this).missingList();
    }

    @Post("/reprovar")
    public void reprovePurchaseOrder(PurchaseOrder purchaseOrder,String justification){
        purchaseOrderService.reprove(purchaseOrder, justification);
        result.redirectTo(this).missingList();
    }

    @Get("/adicionar/informacao/{purchaseOrder.id}")
    public void addInformation(PurchaseOrder purchaseOrder){
        purchaseOrder = purchaseOrderService.findById(purchaseOrder);
        result.include("purchaseOrder", new PurchaseOrderView().generate(purchaseOrder, purchaseOrderService));
        result.include("formsPayment", formPaymentService.findAll());
        result.include("meansPayment", MeanPaymentEnum.values());
        result.redirectTo(this).form();
    }

    @Post("/adicionar/informacao")
    public void addInformations(PurchaseOrder purchaseOrder){
        purchaseOrderService.saveDeliveryAndPayment(purchaseOrder);
        result.forwardTo(this).list();
    }

    @Get("/imprimir/pedido/{purchaseOrder.id}")
    public File printerOrder(PurchaseOrder purchaseOrder){
        return purchaseOrderService.printerOrder(purchaseOrder);
    }

    @Post("/pesquisar/conferencia")
    public void searchConference(PurchaseOrder purchaseOrder){
        purchaseOrder = purchaseOrderService.findByConference(purchaseOrder.getId());
        
        if (purchaseOrder.getBudget().getQuotation().getType().equals(TypeEnum.Material)){
            Collections.sort(purchaseOrder.getOrderRequests(), new Comparator<OrderRequest>() {
                @Override
                public int compare(OrderRequest o1, OrderRequest o2) {
                    return o1.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getProduct().getDescription().compareTo(o2.getBudgetQuotation().getQuotationRequest().getSolicitationRequest().getProduct().getDescription());
                }
            });
        }

        result.include("purchaseOrder",purchaseOrder);
        result.redirectTo(this).formReception();
    }

    @Post("/recepcao/conferir")
    public void conferedReception(Reception reception){
        purchaseOrderService.saveReception(reception, StatusEnum.Conferred);
        result.forwardTo(this).formReception();
    }

    @Post("/recepcao/finalizar")
    public void finalizeReception(Reception reception){
        purchaseOrderService.saveReception(reception, StatusEnum.Finished);
        PurchaseOrder purchaseOrder = purchaseOrderService.findById(reception.getPurchaseOrder());
        result.include("purchaseOrder", purchaseOrder);
        result.forwardTo(this).formSearch();
    }

    @Get("/confirmacao/conferencia/{reception.id}")
    public void confirmConference(Reception reception){
        reception = purchaseOrderService.findReceptionById(reception);
        /** gambiarra **/

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        result.include("totalPrice",decimalFormat.format(purchaseOrderService.sumTotal(reception.getRequestDelivereds())));
        result.include("reception",reception);
        result.include("formsPayment", formPaymentService.findAll());
        result.include("meansPayment", MeanPaymentEnum.values());
        result.redirectTo(this).formConfirmConference();
    }

    @Post("/finalizar/conferencia")
    public void saveConference(Reception reception){
        purchaseOrderService.saveConference(reception);
        result.forwardTo(this).list();
    }

    @Get("/imprimir/ordem/{reception.id}")
    public File printerPurchaseOrder(Reception reception){
        return purchaseOrderService.printerPurchaseOrder(reception);
    }

    @Post("/cancelar")
    public void cancellation(PurchaseOrder purchaseOrder){
        purchaseOrderService.saveCancelation(purchaseOrder);
        result.redirectTo(this).list();
    }

    /**  Listegem  **/
    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength) {
        List<Object[]> purchaseOrderObjects = purchaseOrderService.findPagination(sSearch, iDisplayStart, iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(purchaseOrderService.totalPagination(sSearch));
        dataTableModel.setiTotalDisplayRecords(purchaseOrderService.totalPagination(sSearch));
        dataTableModel.setAaData(purchaseOrderObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/paginar/filtro/{status}")
    public void paginationWithFilter(String sSearch, StatusEnum status ,String sEcho, int iDisplayStart, int iDisplayLength) {
        List<Object[]> purchaseOrderObjects = purchaseOrderService.findPaginationWithFilter(sSearch, status,iDisplayStart, iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(purchaseOrderService.totalPaginationWithFilter(sSearch,status));
        dataTableModel.setiTotalDisplayRecords(purchaseOrderService.totalPaginationWithFilter(sSearch,status));
        dataTableModel.setAaData(purchaseOrderObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/paginar/pendencia")
    public void paginationMissing(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength) {
        List<Object[]> purchaseOrderObjects = purchaseOrderService.findPaginationMissing(sSearch, iDisplayStart, iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(purchaseOrderService.totalPaginationMissing(sSearch));
        dataTableModel.setiTotalDisplayRecords(purchaseOrderService.totalPaginationMissing(sSearch));
        dataTableModel.setAaData(purchaseOrderObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/paginar/pendencia/conferida")
    public void paginationMissingConfered(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength) {
        List<Object[]> purchaseOrderObjects = purchaseOrderService.findPaginationMissingConfered(sSearch, iDisplayStart, iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(purchaseOrderService.totalPaginationMissingConfered(sSearch));
        dataTableModel.setiTotalDisplayRecords(purchaseOrderService.totalPaginationMissingConfered(sSearch));
        dataTableModel.setAaData(purchaseOrderObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    /** validate **/
    @Get("/quantidade/receber/{orderRequest.id}/json")
    public void totalOrderRequestById (OrderRequest orderRequest){
      Float quantity =  purchaseOrderService.getQuantityByOrderRequest(orderRequest);
      Float quantityDelivered = purchaseOrderService.getQuantityDeliveredByOrderRequest(orderRequest);
      Float receivable = quantity - quantityDelivered;
        result.use(Results.json()).withoutRoot().from(receivable).serialize();
    }
}



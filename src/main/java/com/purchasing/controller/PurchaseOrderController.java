package com.purchasing.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.entity.Budget;
import com.purchasing.entity.PurchaseOrder;
import com.purchasing.entity.Supplier;
import com.purchasing.service.impl.PurchaseOrderService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/ordemCompra")
public class PurchaseOrderController {

    private Result result;
    private PurchaseOrderService purchaseOrderService;

    @Deprecated
    public PurchaseOrderController() {
    }

    @Inject
    public PurchaseOrderController(Result result, PurchaseOrderService purchaseOrderService) {
        this.result = result;
        this.purchaseOrderService = purchaseOrderService;
    }

    /** Forms **/
    @Path("/listagem")
    public void list(){
        result.include("controller", this.getClass()).toString();
    }

    @Path("/listagem/pendencia")
    public void missingList(){
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

     /** Actions **/
    @Get("/salvar/unico/{budget.id}")
    public void singleSave(Budget budget) {
         PurchaseOrder purchaseOrder =  purchaseOrderService.singleSave(budget);
        result.include("purchaseOrder",purchaseOrder);
        result.redirectTo(this).visualize();
    }

    @Post("/salvar")
    public void variousSave(PurchaseOrder purchaseOrder){
        result.redirectTo(this).visualize();
    }

    public void searchPurchaseOrderBySupplier(Supplier supplier){
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.findBySupplierOrderDate(supplier);
        result.include("purchaseOrders",purchaseOrders);
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

    @Get("/paginar/pendencia")
    public void paginationMissing(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength) {
        List<Object[]> purchaseOrderObjects = purchaseOrderService.findPagination(sSearch, iDisplayStart, iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
        dataTableModel.setsEcho(sEcho);
        dataTableModel.setiTotalRecords(purchaseOrderService.totalPagination(sSearch));
        dataTableModel.setiTotalDisplayRecords(purchaseOrderService.totalPagination(sSearch));
        dataTableModel.setAaData(purchaseOrderObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

}

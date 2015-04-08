package com.purchasing.controller;

import br.com.caelum.vraptor.*;
import com.purchasing.entity.Budget;
import com.purchasing.entity.PurchaseOrder;
import com.purchasing.service.impl.PurchaseOrderService;

import javax.inject.Inject;

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
    public void list(){
        result.include("controller", this.getClass()).toString();
    }
    public void missingList(){
        result.include("controller", this.getClass()).toString();
    }
    public void formReception(){
        result.include("controller", this.getClass()).toString();
    }
    public void searchSupplier(){
        result.include("controller", this.getClass()).toString();
    }
    public void form(){
        result.include("controller", this.getClass()).toString();
    }
    public void visualize() {
        result.include("controller", this.getClass()).toString();
    }

     /** Actions **/
    @Get("/salvar/unico/{budget.id}")
    public void singleSave(Budget budget) {
        result.redirectTo(this).visualize();
    }

    @Post("/salvar")
    public void variousSave(PurchaseOrder purchaseOrder){
        result.redirectTo(this).visualize();
    }
}

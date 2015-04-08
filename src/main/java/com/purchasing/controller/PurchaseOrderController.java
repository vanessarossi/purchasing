package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import com.purchasing.entity.PurchaseOrder;

import javax.inject.Inject;

/**
 * @author vanessa
 */
@Controller
@Path("/ordemCompra")
public class PurchaseOrderController {

    private Result result;

    @Deprecated
    public PurchaseOrderController() {
    }

    @Inject
    public PurchaseOrderController(Result result) {
        this.result = result;
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
    @Post("/salvar")
    public void save(PurchaseOrder purchaseOrder) {
        result.redirectTo(this).visualize();
    }
}

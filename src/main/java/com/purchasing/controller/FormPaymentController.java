package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.entity.FormPayment;
import com.purchasing.service.impl.FormPaymentService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/formaPagamento")
public class FormPaymentController {

    private Result result;
    private Validator validator;
    private FormPaymentService formPaymentService;

    @Deprecated FormPaymentController(){};

    @Inject
    public FormPaymentController(Result result, Validator validator, FormPaymentService formPaymentService){
        this.result = result;
        this.validator = validator;
        this.formPaymentService = formPaymentService;
    }

    @Path("")
    public void index() {
        result.include("controller", this.getClass().toString());
    }

    @Post("/salvar")
    public void save(@Valid FormPayment formPayment) {
       validator.onErrorForwardTo(this).index();
       formPaymentService.save(formPayment);
       result.redirectTo(this).index();
    }

    @Get("/deletar/{formPayment.id}")
    public void delete(FormPayment formPayment) {
       formPaymentService.delete(formPayment);
       result.redirectTo(this).index();
    }

    @Get("/pesquisar/{formPayment.id}/json")
    public void searchById(FormPayment formPayment){
        FormPayment formPaymentFound= formPaymentService.searchById(formPayment);
        result.use(Results.json()).withoutRoot().from(formPaymentFound).serialize();
    }

    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> formPaymentObjects = formPaymentService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(formPaymentService.totalPagination(sSearch));
            dataTableModel.setiTotalDisplayRecords(formPaymentService.totalPagination(sSearch));
            dataTableModel.setAaData(formPaymentObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }
}

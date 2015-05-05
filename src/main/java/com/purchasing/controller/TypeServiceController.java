package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.annotation.Admin;
import com.purchasing.annotation.Analyst;
import com.purchasing.annotation.Purchaser;
import com.purchasing.entity.TypeService;
import com.purchasing.service.impl.TypeServiceService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/tipoServico")
public class TypeServiceController {

    private Result result;
    private Validator validator;
    private TypeServiceService typeServiceService;

    @Deprecated TypeServiceController(){};

    @Inject
    public TypeServiceController(Result result, Validator validator, TypeServiceService typeServiceService){
        this.result = result;
        this.validator = validator;
        this.typeServiceService = typeServiceService;
    }

    @Purchaser
    @Analyst
    @Admin
    @Path("")
    public void index() {
        result.include("controller", this.getClass().toString());
    }

    @Post("/salvar")
    public void save(@Valid TypeService typeService) {
       validator.onErrorForwardTo(this).index();
       typeServiceService.save(typeService);
       result.redirectTo(this).index();
    }

    @Get("/deletar/{typeService.id}")
    public void delete(TypeService typeService) {
       typeServiceService.delete(typeService);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Get("/pesquisar/{typeService.id}/json")
    public void searchById(TypeService typeService){
        TypeService typeServiceFound= typeServiceService.searchById(typeService);
        result.use(Results.json()).withoutRoot().from(typeServiceFound).serialize();
    }

    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> typeServiceObjects = typeServiceService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(typeServiceService.totalPagination(sSearch));
            dataTableModel.setiTotalDisplayRecords(typeServiceService.totalPagination(sSearch));
            dataTableModel.setAaData(typeServiceObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }
}


package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.entity.Unit;
import com.purchasing.service.impl.UnitService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/unidade")
public class UnitController {

    private Result result;
    private UnitService unitService;
    private Validator validator;

    @Deprecated UnitController(){};

    @Inject
    public UnitController(Result result, UnitService unitService, Validator validator){
        this.result = result;
        this.unitService = unitService;
        this.validator = validator;
    }

    @Path("")
    public void index() {
        result.include("controller", this.getClass().toString());
    }

    @Post("/salvar")
    public void save(@Valid Unit unit) {
       validator.onErrorForwardTo(this).index();
       Unit unitSaved = unitService.save(unit);
       result.redirectTo(this).index();
    }

    @Get("/deletar/{unit.id}")
    public void delete(Unit unit) {
       unitService.delete(unit);
       result.redirectTo(this).index();
    }

    @Get("/pesquisar/{unit.id}/json")
    public void searchById(Unit unit){
        Unit unitFound = unitService.searchById(unit);
        result.use(Results.json()).withoutRoot().from(unitFound).serialize();
    }

    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> unitObjects = unitService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(unitService.totalPagination(sSearch));
            dataTableModel.setiTotalDisplayRecords(unitService.totalPagination(sSearch));
            dataTableModel.setAaData(unitObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }
}

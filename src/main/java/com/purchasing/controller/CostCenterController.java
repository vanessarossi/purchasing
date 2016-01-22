package com.purchasing.controller;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.access.EighthLevelAccessRule;
import com.purchasing.entity.Company;
import com.purchasing.entity.CostCenter;
import com.purchasing.service.impl.CostCenterService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/centroCusto")
public class CostCenterController {
    private Result result;
    private Validator validator;
    private CostCenterService costCenterService;

    @Deprecated CostCenterController(){};

    @Inject
    public CostCenterController(Result result, Validator validator, CostCenterService costCenterService){
        this.result = result;
        this.validator = validator;
        this.costCenterService = costCenterService;
    }

    @CustomBrutauthRules(EighthLevelAccessRule.class)
    @Path("")
    public void index() {
        result.include("controller", this.getClass().toString());
    }

    @Post("/salvar")
    public void save(@Valid CostCenter costCenter){
        validator.onErrorForwardTo(this).index();
        costCenterService.save(costCenter);
        result.redirectTo(this).index();
    }

    @Post("/empresas/json")
    public void getCompanies(){
        List<Company> companies = costCenterService.findAllCompany();
        result.use(Results.json()).withoutRoot().from(companies).serialize();
    }

    @Get("/deletar/{costCenter.id}")
    public void deletar(CostCenter costCenter){
        costCenterService.delete(costCenter);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Get("/pesquisar/{costCenter.id}/json")
    public void searchById(CostCenter costCenter){
        CostCenter costCenterSaved = costCenterService.searchById(costCenter);
        result.use(Results.json()).withoutRoot().from(costCenterSaved).include("company").serialize();
    }

    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> costCenterObjects = costCenterService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(costCenterService.totalPagination(sSearch));
            dataTableModel.setiTotalDisplayRecords(costCenterService.totalPagination(sSearch));
            dataTableModel.setAaData(costCenterObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/pesquisar/empresa/{company.id}/json")
    public void searchByCompany(Company company){
        List<CostCenter> costCenters = costCenterService.searchByCompany(company);
        result.use(Results.json()).withoutRoot().from(costCenters).serialize();
    }
}

package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.entity.Category;
import com.purchasing.service.impl.CategoryService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/categoria")
public class CategoryController {

    private Result result;
    private CategoryService categoryService;
    private Validator validator;

    @Deprecated CategoryController(){};

    @Inject
    public CategoryController(Result result, CategoryService categoryService, Validator validator){
        this.result = result;
        this.categoryService = categoryService;
        this.validator = validator;
    }

    @Path("")
    public void index() {
        result.include("controller", this.getClass().toString());
    }

    @Post("/salvar")
    public void save(@Valid Category category) {
       validator.onErrorForwardTo(this).index();
       categoryService.save(category);
       result.redirectTo(this).index();
    }

    @Get("/deletar/{category.id}")
    public void delete(Category category) {
       categoryService.delete(category);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Get("/pesquisar/{category.id}/json")
    public void searchById(Category category){
        Category categoryFound = categoryService.searchById(category);
        result.use(Results.json()).withoutRoot().from(categoryFound).serialize();
    }

    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> categoryObjects = categoryService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(categoryService.totalPagination(sSearch));
            dataTableModel.setiTotalDisplayRecords(categoryService.totalPagination(sSearch));
            dataTableModel.setAaData(categoryObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }
}

package com.purchasing.controller;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.access.TenthLevelAccessRule;
import com.purchasing.entity.User;
import com.purchasing.service.impl.UserService;
import com.purchasing.support.datatable.DataTableModel;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author vanessa
 */
@Controller
@Path("/usuario")
public class UserController {

    private Result result;
    private Validator validator;
    private UserService userService;

    @Deprecated UserController(){};

    @Inject
    public UserController(Result result, Validator validator, UserService userService){
        this.result = result;
        this.validator = validator;
        this.userService = userService;
    }

    @CustomBrutauthRules(TenthLevelAccessRule.class)
    @Path("")
    public void list() {
        result.include("controller", this.getClass().toString());
    }

    @CustomBrutauthRules(TenthLevelAccessRule.class)
    @Path("/formulario")
     public void form() {
         result.include("companies",userService.findAllCompany());
         result.include("roles", userService.findAllRole());
         result.include("controller", this.getClass().toString());
     }

    @Post("/salvar")
    public void save(@Valid User user){
        validator.ensure(user.getRole().getId() != null , new I18nMessage("user.role","message.notBlank"));
        validator.onErrorForwardTo(this).form();
        userService.save(user);
        result.redirectTo(this).list();
    }

    @Get("/deletar/{user.id}")
    public void delete(User user) {
        userService.delete(user);
        result.use(Results.json()).withoutRoot().from(true).serialize();
    }

    @Get("/editar/{user.id}")
    public void edit(User user) {
        user = userService.searchById(user);
        result.include("user", user);
        result.redirectTo(this).form();
    }

    @Get("/paginar")
    public void pagination(String sSearch, String sEcho, int iDisplayStart, int iDisplayLength){
        List<Object[]> userObjects = userService.findPagination(sSearch,iDisplayStart,iDisplayLength);
        DataTableModel dataTableModel = new DataTableModel();
            dataTableModel.setsEcho(sEcho);
            dataTableModel.setiTotalRecords(userService.totalPagination(sSearch));
            dataTableModel.setiTotalDisplayRecords(userService.totalPagination(sSearch));
            dataTableModel.setAaData(userObjects.toArray());
        result.use(Results.json()).withoutRoot().from(dataTableModel).include("aaData").serialize();
    }

    @Get("/pesquisar/{user.id}/json")
    public void searchById(User user){
        User userFound = userService.searchById(user);
        result.use(Results.json()).withoutRoot().from(userFound).include("role").include("costCenters").include("costCenters.company").serialize();
    }
}
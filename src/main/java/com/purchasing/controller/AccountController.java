package com.purchasing.controller;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import com.purchasing.access.EighthLevelAccessRule;
import com.purchasing.entity.Account;
import com.purchasing.enumerator.AddressEnum;
import com.purchasing.service.impl.AccountService;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanessa on 20/04/2016.
 */
@Controller
@Path("/conta")
public class AccountController {

    private Result result;
    private AccountService accountService;
    private Validator validator;

    @Deprecated AccountController(){};

    @Inject
    public AccountController(Result result, AccountService accountService, Validator validator){
        this.result = result;
        this.accountService = accountService;
        this.validator = validator;
    }

    /** FORM **/
    @CustomBrutauthRules(EighthLevelAccessRule.class)
    @Path("/nova")
    public void form(){
        result.include("addresses",accountService.getAllAddress());
    }

    @CustomBrutauthRules(EighthLevelAccessRule.class)
    @Path("/listagem")
    public void list(){
        result.include("addresses",accountService.getAllAddress());
    }

    /** ACTION **/
    @Post("/salvar")
    public void save(@Valid Account account){
        validator.onErrorForwardTo(this).form();
        accountService.save(account);
        result.redirectTo(this).list();
    }

    @Get("/pesquisa/lugar/{address}/json")
    public void searchByAddress(AddressEnum address){
        String place = accountService.searchPlace(address);
      if(place != "Não Encontrado"){
          result.use(Results.json()).withoutRoot().from(place).serialize();
      }else{
          result.use(Results.json()).withoutRoot().from(false).serialize() ;
      }
    }

    @Post("/pesquisa/lancamentos/json")
    public void search(Account account){
        List<Account> accounts = new ArrayList<>();
        accounts = accountService.searchByTypeAndCompetenceAndAddress(account);
        if (accounts != null){
            result.use(Results.json()).withoutRoot().from(accounts).include("address").serialize();
        }else{
            result.use(Results.json()).withoutRoot().from(false).serialize() ;
        }
    }

    @Get("/editar/{account.id}")
    public void searchById(Account account){
        account = accountService.searchById(account);
        result.include("account",account);
        result.forwardTo(this).form();
    }

    @Get("/deletar/{account.id}")
    public void remove(Account account){
        account = accountService.searchById(account);
        accountService.delete(account);
        result.forwardTo(this).list();
    }
}

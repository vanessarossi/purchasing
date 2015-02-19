package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

import javax.inject.Inject;

/**
 * @author vanessa
 */
@Controller
@Path("/contrato")
public class ContractController {

    private Result result;

    @Deprecated ContractController(){};

    @Inject
    public ContractController(Result result){
        this.result = result;
    }

    @Path({ "", "/" })
    public void list() {
        result.include("controller", this.getClass().toString());
    }
}

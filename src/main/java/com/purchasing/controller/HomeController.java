package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

import javax.inject.Inject;

/**
 * @author vanessa
 */
@Controller
@Path("/home")
public class HomeController {

    private Result result;

    @Deprecated HomeController(){};

    @Inject
    public HomeController(Result result){
        this.result = result;
    }

    @Path("")
    public void index() {
        result.include("controller", this.getClass().toString());
    }

}

package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

import javax.inject.Inject;

/**
 * @author Vanessa
 */

@Controller
@Path("/relatorio")
public class ReportController {

    private Result result;


    @Deprecated
    public ReportController() {
    }

    @Inject
    public ReportController(Result result) {
        this.result = result;
    }

    /** Pages  **/
    @Path("/recepcao/data")
    public void gerencialReport(){
        result.include("controller", this.getClass().toString());
    }

}

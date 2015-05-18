package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import com.purchasing.printer.report.ReceptionByDatePrinter;

import javax.inject.Inject;
import java.io.File;
import java.util.Date;

/**
 * @author Vanessa
 */

@Controller
@Path("/relatorio")
public class ReportController {

    private Result result;
    private ReceptionByDatePrinter receptionByDatePrinter;

    @Deprecated
    public ReportController() {
    }

    @Inject
    public ReportController(Result result, ReceptionByDatePrinter receptionByDatePrinter) {
        this.result = result;
        this.receptionByDatePrinter = receptionByDatePrinter;
    }

    /** Pages  **/
    @Path("/recepcao/data")
    public void receptionByDate(){
        result.include("controller", this.getClass().toString());
    }


    /** Results  **/
    @Post("/recepcao/data/resultado")
    public File receptionsByDate(Date initialDate, Date finalDate){
        return receptionByDatePrinter.generateOrder(initialDate,finalDate);
    }
}

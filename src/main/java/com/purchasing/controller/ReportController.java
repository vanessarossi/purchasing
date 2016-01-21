package com.purchasing.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.Download;
import com.purchasing.service.impl.ReportService;

import javax.inject.Inject;

/**
 * @author Vanessa
 */

@Controller
@Path("/relatorio")
public class ReportController {

    private Result result;
    private ReportService reportService;

    @Deprecated
    public ReportController() {
    }

    @Inject
    public ReportController(Result result, ReportService reportService) {
        this.result = result;
        this.reportService = reportService;
    }

    /** Pages  **/
    @Path("/")
    public void index(){
        result.include("controller", this.getClass().toString());
    }

    @Path("/financeiro")
    public Download paymentForecastReport(){
      return reportService.exportPaymentForecastReport();
    }
}

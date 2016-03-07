package com.purchasing.controller;

import br.com.caelum.brutauth.auth.annotations.CustomBrutauthRules;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.Download;
import com.purchasing.access.NinthLevelAccessRule;
import com.purchasing.access.SecondLevelAccessRule;
import com.purchasing.service.impl.ReportService;
import com.purchasing.support.excel.entity.principal.Report;

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
    @CustomBrutauthRules(SecondLevelAccessRule.class)
    @Path("/")
    public void index(){
        result.include("controller", this.getClass().toString());
    }

    @CustomBrutauthRules(NinthLevelAccessRule.class)
    @Path("/financeiro")
    public Download paymentForecastReport(){
      return reportService.exportPaymentForecastReport();
    }

    @CustomBrutauthRules(SecondLevelAccessRule.class)
    @Post("/financeiro/gerencial")
    public Download financialManagementReport(Report report){
        return reportService.exportFinancialManagementReport(report);
    }

    @CustomBrutauthRules(SecondLevelAccessRule.class)
    @Post("/financeiro/gerencial/centro/custo")
    public Download financialManagementByCostCenterReport(Report report){
        return reportService.exportFinancialManagementByCostCenterReport(report);
    }

    @CustomBrutauthRules(SecondLevelAccessRule.class)
    @Post("/relacao/ordem/fornecedor/vencimento")
    public Download purchaseOrderForSupplierAndExpirationDateReport(Report report){
        return reportService.exportPurchaseOrderForSupplierAndExpirationDateReport(report);
    }

    @CustomBrutauthRules(SecondLevelAccessRule.class)
    @Post("/relacao/compras/classificada/categoria/produto")
    public Download purchasedProductClassificationReport(Report report){
        return reportService.exportPurchasedProductClassificationReport(report);
    }

    @CustomBrutauthRules(SecondLevelAccessRule.class)
    @Post("/relacao/compras/classificada/categoria/produto/centro/custo")
    public Download purchasedProductClassificationByCostCenterReport(Report report){
        return reportService.exportPurchasedProductClassificationByCostCenterReport(report);
    }

    @CustomBrutauthRules(SecondLevelAccessRule.class)
    @Post("/relacao/compras/classificada/tipo/servico")
    public Download purchasedServiceTypeReport(Report report){
        return reportService.exportPurchasedServiceTypeReport(report);
    }

    @CustomBrutauthRules(SecondLevelAccessRule.class)
    @Post("/relacao/compras/classificada/tipo/servico/centro/custo")
    public Download purchasedServiceTypeByCostCenterReport(Report report){
        return reportService.exportPurchasedServiceTypeByCostCenterReport(report);
    }

    @CustomBrutauthRules(SecondLevelAccessRule.class)
    @Post("/relacao/solicitacao/ordem/compra")
    public Download purchaseOrderAndSolicitationReport(Report report){
        return reportService.exportPurchaseOrderAndSolicitationReport(report);
    }
}


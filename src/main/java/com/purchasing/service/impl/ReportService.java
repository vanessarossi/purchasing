package com.purchasing.service.impl;

import br.com.caelum.vraptor.observer.download.FileDownload;
import com.purchasing.support.excel.entity.principal.Report;

/**
 * Created by Vanessa on 12/29/15.
 */
public interface ReportService {

    public FileDownload exportPaymentForecastReport();

    public FileDownload exportFinancialManagementReport(Report report);

    public FileDownload exportPurchaseOrderForSupplierAndExpirationDateReport(Report report);

    public FileDownload exportPurchasedProductClassificationReport(Report report);

    public FileDownload exportPurchasedServiceTypeReport(Report report);

    public FileDownload exportManagementByCostCenterReport(Report report);
}

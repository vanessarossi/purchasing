package com.purchasing.service;

import br.com.caelum.vraptor.observer.download.FileDownload;
import com.purchasing.dao.ReportDAO;
import com.purchasing.service.impl.ReportService;
import com.purchasing.support.excel.*;
import com.purchasing.support.excel.entity.principal.Report;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;

/**
 * Created by Vanessa on 12/29/15.
 */
public class ReportServiceImpl implements ReportService {

    @Inject
    private ReportDAO reportDAO;

    @Inject
    private ExportPaymentForecastReport exportPaymentForecastReport;
    @Inject
    private ExportFinancialManagementReport exportFinancialManagementReport;
    @Inject
    private ExportPurchaseOrderForSupplierAndExpirationDateReport exportPurchaseOrderForSupplierAndExpirationDateReport;
    @Inject
    private ExportPurchasedProductClassificationReport exportPurchasedProductClassificationReport;
    @Inject
    private ExportPurchasedServiceTypeReport exportPurchasedServiceTypeReport;
    @Inject
    private ExportPurchaseOrderAndSolicitationReport exportPurchaseOrderAndSolicitationReport;

    @Override
    public FileDownload exportPaymentForecastReport() {
        Collection<Object> objects =  reportDAO.getPaymentForecastReport();
        File file = exportPaymentForecastReport.generateReport(objects);

        FileDownload fileDownload = null;
        String nameFile = file.getName();
        String contentType = "application/vnd.ms-excel";

        try {
            fileDownload = new FileDownload(file,contentType,nameFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileDownload;
    }

    @Override
    public FileDownload exportFinancialManagementReport(Report report) {
        Collection<Object> objects = reportDAO.getFinancialManagementReport(report);
        File file = exportFinancialManagementReport.generateReport(objects);

        FileDownload fileDownload = null;
        String nameFile = file.getName();
        String contentType = "application/vnd.ms-excel";

        try {
            fileDownload = new FileDownload(file,contentType,nameFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileDownload;
    }

    @Override
    public FileDownload exportPurchaseOrderForSupplierAndExpirationDateReport(Report report) {
        Collection<Object> objects = reportDAO.getPurchaseOrderForSupplierAndExpirationDateReport(report);
        File file = exportPurchaseOrderForSupplierAndExpirationDateReport.generateReport(objects);

        FileDownload fileDownload = null;
        String nameFile = file.getName();
        String contentType = "application/vnd.ms-excel";

        try {
            fileDownload = new FileDownload(file,contentType,nameFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileDownload;
    }

    @Override
    public FileDownload exportPurchasedProductClassificationReport(Report report) {
        Collection<Object> objects = reportDAO.getTotalPurchasedProductClassificationReport(report);
        File file = exportPurchasedProductClassificationReport.generateReport(objects);

        FileDownload fileDownload = null;
        String nameFile = file.getName();
        String contentType = "application/vnd.ms-excel";

        try {
            fileDownload = new FileDownload(file,contentType,nameFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileDownload;
    }

    @Override
    public FileDownload exportPurchasedServiceTypeReport(Report report) {
        Collection<Object> objects = reportDAO.getTotalPurchasedServiceTypeReport(report);
        File file = exportPurchasedServiceTypeReport.generateReport(objects);

        FileDownload fileDownload = null;
        String nameFile = file.getName();
        String contentType = "application/vnd.ms-excel";

        try {
            fileDownload = new FileDownload(file,contentType,nameFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileDownload;
    }

    @Override
    public FileDownload exportManagementByCostCenterReport(Report report) {
        return null;
    }

    @Override
    public FileDownload exportPurchaseOrderAndSolicitationReport(Report report) {
        File file = exportPurchaseOrderAndSolicitationReport.generateReportWithMultipleSearch(report);

        FileDownload fileDownload = null;
        String nameFile = file.getName();
        String contentType = "application/vnd.ms-excel";

        try {
            fileDownload = new FileDownload(file,contentType,nameFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileDownload;
    }


}

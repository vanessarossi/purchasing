package com.purchasing.service;

import br.com.caelum.vraptor.observer.download.FileDownload;
import com.purchasing.dao.ReportDAO;
import com.purchasing.service.impl.ReportService;
import com.purchasing.support.excel.ExportPaymentForecastReport;

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

    @Override
    public FileDownload exportPaymentForecastReport() {
        Collection<Object> objects =  reportDAO.getPaymentForecastReport();
        File file = exportPaymentForecastReport.generatReport(objects);

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

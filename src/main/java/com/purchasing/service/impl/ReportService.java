package com.purchasing.service.impl;

import br.com.caelum.vraptor.observer.download.FileDownload;

/**
 * Created by Vanessa on 12/29/15.
 */
public interface ReportService {

    public FileDownload exportPaymentForecastReport();
}

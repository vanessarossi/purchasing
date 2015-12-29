package com.purchasing.service;

import com.purchasing.dao.ReportDAO;
import com.purchasing.service.impl.ReportService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Vanessa on 12/29/15.
 */
public class ReportServiceImpl implements ReportService {

    @Inject
    private ReportDAO reportDAO;

    @Override
    public List<Object> getPaymentForecastReport() {
        List<Object> objects =  reportDAO.getPaymentForecastReport();



        return objects;
    }
}

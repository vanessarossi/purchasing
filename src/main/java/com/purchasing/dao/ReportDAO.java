package com.purchasing.dao;

import com.purchasing.dao.base.DAOImpl;
import org.hibernate.Session;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Vanessa on 12/29/15.
 */
public class ReportDAO extends DAOImpl<Object,Integer> {

    @Inject
    public ReportDAO(Session session) {
        super(session);
    }

    public Collection<Object> getPaymentForecastReport(){
        Collection<Object> objects = getSession().createSQLQuery("SELECT * FROM payment_forecast_report").list();
       return  objects ;
    }
}
